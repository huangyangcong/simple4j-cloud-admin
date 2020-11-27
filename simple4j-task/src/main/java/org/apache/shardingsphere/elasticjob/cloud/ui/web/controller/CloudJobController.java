/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.elasticjob.cloud.ui.web.controller;

import com.google.common.base.Strings;
import com.simple4j.web.bean.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.cloud.config.CloudJobExecutionType;
import org.apache.shardingsphere.elasticjob.cloud.config.pojo.CloudJobConfigurationPOJO;
import org.apache.shardingsphere.elasticjob.cloud.statistics.StatisticInterval;
import org.apache.shardingsphere.elasticjob.cloud.statistics.type.job.JobExecutionTypeStatistics;
import org.apache.shardingsphere.elasticjob.cloud.statistics.type.job.JobRegisterStatistics;
import org.apache.shardingsphere.elasticjob.cloud.statistics.type.job.JobRunningStatistics;
import org.apache.shardingsphere.elasticjob.cloud.statistics.type.task.TaskResultStatistics;
import org.apache.shardingsphere.elasticjob.cloud.statistics.type.task.TaskRunningStatistics;
import org.apache.shardingsphere.elasticjob.cloud.ui.service.FacadeService;
import org.apache.shardingsphere.elasticjob.cloud.ui.service.job.CloudJobConfigurationService;
import org.apache.shardingsphere.elasticjob.cloud.ui.service.producer.ProducerService;
import org.apache.shardingsphere.elasticjob.cloud.ui.service.state.failover.FailoverTaskInfo;
import org.apache.shardingsphere.elasticjob.cloud.ui.service.statistics.StatisticManager;
import org.apache.shardingsphere.elasticjob.cloud.ui.web.controller.search.JobEventRdbSearch;
import org.apache.shardingsphere.elasticjob.infra.context.TaskContext;
import org.apache.shardingsphere.elasticjob.infra.exception.JobSystemException;
import org.apache.shardingsphere.elasticjob.tracing.event.JobExecutionEvent;
import org.apache.shardingsphere.elasticjob.tracing.event.JobStatusTraceEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

/**
 * Cloud job restful api.
 */
@Slf4j
@RestController
@RequestMapping("/api/job")
public final class CloudJobController {

	@Autowired
	private JobEventRdbSearch jobEventRdbSearch;

	@Autowired
	private ProducerService producerManager;

	@Autowired
	private CloudJobConfigurationService jobConfigService;

	@Autowired
	private FacadeService facadeService;

	@Autowired
	private StatisticManager statisticManager;

	/**
	 * Register cloud job.
	 *
	 * @param cloudJobConfig cloud job configuration
	 */
	@PostMapping("/register")
	public ApiResponse<Boolean> register(
		@RequestBody final CloudJobConfigurationPOJO cloudJobConfig) {
		producerManager.register(cloudJobConfig);
		return ApiResponse.ok(Boolean.TRUE);
	}

	/**
	 * Update cloud job.
	 *
	 * @param cloudJobConfig cloud job configuration
	 */
	@PutMapping("/update")
	public ApiResponse<Boolean> update(
		@RequestBody final CloudJobConfigurationPOJO cloudJobConfig) {
		producerManager.update(cloudJobConfig);
		return ApiResponse.ok(Boolean.TRUE);
	}

	/**
	 * Deregister cloud job.
	 *
	 * @param jobName job name
	 */
	@DeleteMapping("/{jobName}/deregister")
	public ApiResponse<Boolean> deregister(@PathVariable final String jobName) {
		producerManager.deregister(jobName);
		return ApiResponse.ok(Boolean.TRUE);
	}

	/**
	 * Check whether the cloud job is disabled or not.
	 *
	 * @param jobName job name
	 * @return true is disabled, otherwise not
	 */
	@GetMapping("/{jobName}/disable")
	public ApiResponse<Boolean> isDisabled(@PathVariable("jobName") final String jobName) {
		boolean result = facadeService.isJobDisabled(jobName);
		return ApiResponse.ok(result);
	}

	/**
	 * Enable cloud job.
	 *
	 * @param jobName job name
	 */
	@PostMapping("/{jobName}/enable")
	public ApiResponse<Boolean> enable(@PathVariable("jobName") final String jobName) {
		Optional<CloudJobConfigurationPOJO> configOptional = jobConfigService.load(jobName);
		if (configOptional.isPresent()) {
			facadeService.enableJob(jobName);
		}
		return ApiResponse.ok(Boolean.TRUE);
	}

	/**
	 * Disable cloud job.
	 *
	 * @param jobName job name
	 */
	@PostMapping("/{jobName}/disable")
	public ApiResponse<Boolean> disable(@PathVariable("jobName") final String jobName) {
		if (jobConfigService.load(jobName).isPresent()) {
			facadeService.disableJob(jobName);
		}
		return ApiResponse.ok(Boolean.TRUE);
	}

	/**
	 * Trigger job once.
	 *
	 * @param jobName job name
	 */
	@PostMapping("/trigger")
	public ApiResponse<Boolean> trigger(@RequestBody final String jobName) {
		Optional<CloudJobConfigurationPOJO> config = jobConfigService.load(jobName);
		if (config.isPresent() && CloudJobExecutionType.DAEMON == config.get()
			.getJobExecutionType()) {
			throw new JobSystemException("Daemon job '%s' cannot support trigger.", jobName);
		}
		facadeService.addTransient(jobName);
		return ApiResponse.ok(Boolean.TRUE);
	}

	/**
	 * Query job detail.
	 *
	 * @param jobName job name
	 * @return the job detail
	 */
	@GetMapping("/jobs/{jobName}")
	public ApiResponse<CloudJobConfigurationPOJO> detail(
		@PathVariable("jobName") final String jobName) {
		Optional<CloudJobConfigurationPOJO> cloudJobConfig = jobConfigService.load(jobName);
		return ApiResponse.ok(cloudJobConfig.orElse(null));
	}

	/**
	 * Find all jobs.
	 *
	 * @return all jobs
	 */
	@GetMapping("/jobs")
	public ApiResponse<Collection<CloudJobConfigurationPOJO>> findAllJobs() {
		return ApiResponse.ok(jobConfigService.loadAll());
	}

	/**
	 * Find all running tasks.
	 *
	 * @return all running tasks
	 */
	@GetMapping("tasks/running")
	public ApiResponse<Collection<TaskContext>> findAllRunningTasks() {
		List<TaskContext> result = new LinkedList<>();
		for (Set<TaskContext> each : facadeService.getAllRunningTasks().values()) {
			result.addAll(each);
		}
		return ApiResponse.ok(result);
	}

	/**
	 * Find all ready tasks.
	 *
	 * @return collection of all ready tasks
	 */
	@GetMapping("tasks/ready")
	public ApiResponse<Collection<Map<String, String>>> findAllReadyTasks() {
		Map<String, Integer> readyTasks = facadeService.getAllReadyTasks();
		List<Map<String, String>> result = new ArrayList<>(readyTasks.size());
		for (Entry<String, Integer> each : readyTasks.entrySet()) {
			Map<String, String> oneTask = new HashMap<>(2, 1);
			oneTask.put("jobName", each.getKey());
			oneTask.put("times", String.valueOf(each.getValue()));
			result.add(oneTask);
		}
		return ApiResponse.ok(result);
	}

	/**
	 * Find all failover tasks.
	 *
	 * @return collection of all the failover tasks
	 */
	@GetMapping("tasks/failover")
	public ApiResponse<Collection<FailoverTaskInfo>> findAllFailoverTasks() {
		List<FailoverTaskInfo> result = new LinkedList<>();
		for (Collection<FailoverTaskInfo> each : facadeService.getAllFailoverTasks().values()) {
			result.addAll(each);
		}
		return ApiResponse.ok(result);
	}

	/**
	 * Find job execution events.
	 *
	 * @param requestParams request params
	 * @return job execution event
	 * @throws ParseException parse exception
	 */
	@PostMapping("/events/executions")
	public ApiResponse<JobEventRdbSearch.Result<JobExecutionEvent>> findJobExecutionEvents(
		@RequestParam final MultiValueMap<String, String> requestParams) throws ParseException {
		if (!isRdbConfigured()) {
			return ApiResponse.ok(
				new JobEventRdbSearch.Result<>(0, Collections.<JobExecutionEvent>emptyList()));
		}
		return ApiResponse.ok(
			jobEventRdbSearch.findJobExecutionEvents(
				buildCondition(requestParams,
					new String[]{"jobName", "taskId", "ip", "isSuccess"})));
	}

	/**
	 * Find job status trace events.
	 *
	 * @param requestParams request params
	 * @return job status trace event
	 * @throws ParseException parse exception
	 */
	@PostMapping("/events/statusTraces")
	public ApiResponse<JobEventRdbSearch.Result<JobStatusTraceEvent>> findJobStatusTraceEvents(
		@RequestParam final MultiValueMap<String, String> requestParams) throws ParseException {
		if (!isRdbConfigured()) {
			return ApiResponse.ok(
				new JobEventRdbSearch.Result<>(0, Collections.<JobStatusTraceEvent>emptyList()));
		}
		return ApiResponse.ok(
			jobEventRdbSearch.findJobStatusTraceEvents(
				buildCondition(
					requestParams,
					new String[]{
						"jobName", "taskId", "slaveId", "source", "executionType", "state"
					})));
	}

	private boolean isRdbConfigured() {
		return jobEventRdbSearch.isEnable();
	}

	private JobEventRdbSearch.Condition buildCondition(
		final MultiValueMap<String, String> requestParams, final String[] params)
		throws ParseException {
		int perPage = 10;
		int page = 1;
		if (!Strings.isNullOrEmpty(requestParams.getFirst("per_page"))) {
			perPage = Integer.parseInt(requestParams.getFirst("per_page"));
		}
		if (!Strings.isNullOrEmpty(requestParams.getFirst("page"))) {
			page = Integer.parseInt(requestParams.getFirst("page"));
		}
		String sort = requestParams.getFirst("sort");
		String order = requestParams.getFirst("order");
		Date startTime = null;
		Date endTime = null;
		Map<String, Object> fields = getQueryParameters(requestParams, params);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (!Strings.isNullOrEmpty(requestParams.getFirst("startTime"))) {
			startTime = simpleDateFormat.parse(requestParams.getFirst("startTime"));
		}
		if (!Strings.isNullOrEmpty(requestParams.getFirst("endTime"))) {
			endTime = simpleDateFormat.parse(requestParams.getFirst("endTime"));
		}
		return new JobEventRdbSearch.Condition(perPage, page, sort, order, startTime, endTime,
			fields);
	}

	private Map<String, Object> getQueryParameters(
		final MultiValueMap<String, String> requestParams, final String[] params) {
		final Map<String, Object> result = new HashMap<>();
		for (String each : params) {
			if (!Strings.isNullOrEmpty(requestParams.getFirst(each))) {
				result.put(each, requestParams.getFirst(each));
			}
		}
		return result;
	}

	/**
	 * Find task result statistics.
	 *
	 * @param since time span
	 * @return task result statistics
	 */
	@GetMapping("/statistics/tasks/results")
	public ApiResponse<List<TaskResultStatistics>> findTaskResultStatistics(
		@RequestParam(value = "since", required = false) final String since) {
		if ("last24hours".equals(since)) {
			return ApiResponse.ok(statisticManager.findTaskResultStatisticsDaily());
		} else {
			return ApiResponse.ok(Collections.emptyList());
		}
	}

	/**
	 * Get task result statistics.
	 *
	 * @param period time period
	 * @return task result statistics
	 */
	@GetMapping("/statistics/tasks/results/{period}")
	public ApiResponse<TaskResultStatistics> getTaskResultStatistics(
		@PathVariable(value = "period", required = false) final String period) {
		TaskResultStatistics taskResultStatistics;
		switch (period) {
			case "online":
				taskResultStatistics = statisticManager.getTaskResultStatisticsSinceOnline();
				break;
			case "lastWeek":
				taskResultStatistics = statisticManager.getTaskResultStatisticsWeekly();
				break;
			case "lastHour":
				taskResultStatistics =
					statisticManager.findLatestTaskResultStatistics(StatisticInterval.HOUR);
				break;
			case "lastMinute":
				taskResultStatistics =
					statisticManager.findLatestTaskResultStatistics(StatisticInterval.MINUTE);
				break;
			default:
				taskResultStatistics = new TaskResultStatistics(0, 0, StatisticInterval.DAY,
					new Date());
		}
		return ApiResponse.ok(taskResultStatistics);
	}

	/**
	 * Find task running statistics.
	 *
	 * @param since time span
	 * @return task result statistics
	 */
	@GetMapping("/statistics/tasks/running")
	public ApiResponse<List<TaskRunningStatistics>> findTaskRunningStatistics(
		@RequestParam(value = "since", required = false) final String since) {
		if ("lastWeek".equals(since)) {
			return ApiResponse.ok(statisticManager.findTaskRunningStatisticsWeekly());
		} else {
			return ApiResponse.ok(Collections.emptyList());
		}
	}

	/**
	 * Get job execution type statistics.
	 *
	 * @return job execution statistics
	 */
	@GetMapping("/statistics/jobs/executionType")
	public ApiResponse<JobExecutionTypeStatistics> getJobExecutionTypeStatistics() {
		return ApiResponse.ok(statisticManager.getJobExecutionTypeStatistics());
	}

	/**
	 * Find job running statistics in the recent week.
	 *
	 * @param since time span
	 * @return collection of job running statistics in the recent week
	 */
	@GetMapping("/statistics/jobs/running")
	public ApiResponse<List<JobRunningStatistics>> findJobRunningStatistics(
		@RequestParam(value = "since", required = false) final String since) {
		if ("lastWeek".equals(since)) {
			return ApiResponse.ok(statisticManager.findJobRunningStatisticsWeekly());
		} else {
			return ApiResponse.ok(Collections.emptyList());
		}
	}

	/**
	 * Find job register statistics.
	 *
	 * @return collection of job register statistics since online
	 */
	@GetMapping("/statistics/jobs/register")
	public ApiResponse<List<JobRegisterStatistics>> findJobRegisterStatistics() {
		return ApiResponse.ok(statisticManager.findJobRegisterStatisticsSinceOnline());
	}
}
