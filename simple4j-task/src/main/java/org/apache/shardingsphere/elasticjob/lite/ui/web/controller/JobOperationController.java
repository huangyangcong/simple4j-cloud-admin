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

package org.apache.shardingsphere.elasticjob.lite.ui.web.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import com.simple4j.web.bean.ApiResponse;
import org.apache.shardingsphere.elasticjob.lite.lifecycle.domain.JobBriefInfo;
import org.apache.shardingsphere.elasticjob.lite.lifecycle.domain.ShardingInfo;
import org.apache.shardingsphere.elasticjob.lite.ui.service.JobAPIService;
import org.apache.shardingsphere.elasticjob.util.SessionRegistryCenterConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Job operation RESTful API.
 */
@RestController
@RequestMapping("/api/jobs")
public final class JobOperationController {

	private JobAPIService jobAPIService;

	@Autowired
	public JobOperationController(final JobAPIService jobAPIService) {
		this.jobAPIService = jobAPIService;
	}

	/**
	 * Get jobs total count.
	 *
	 * @return jobs total count
	 */
	@GetMapping("/count")
	public int getJobsTotalCount() {
		return jobAPIService.getJobStatisticsAPI().getJobsTotalCount();
	}

	/**
	 * Get all jobs brief info.
	 *
	 * @return all jobs brief info
	 */
	@GetMapping("/getAllJobsBriefInfo")
	public ApiResponse<Collection<JobBriefInfo>> getAllJobsBriefInfo() {
		Collection<JobBriefInfo> data =
			Objects.nonNull(SessionRegistryCenterConfiguration.getRegistryCenterConfiguration())
				? jobAPIService.getJobStatisticsAPI().getAllJobsBriefInfo()
				: Collections.emptyList();
		return ApiResponse.ok(data);
	}

	/**
	 * Trigger job.
	 *
	 * @param jobName job name
	 */
	@PostMapping("/{jobName}/trigger")
	public ApiResponse<Boolean> triggerJob(@PathVariable("jobName") final String jobName) {
		jobAPIService.getJobOperatorAPI().trigger(jobName);
		return ApiResponse.ok(Boolean.TRUE);
	}

	/**
	 * Disable job.
	 *
	 * @param jobName job name
	 */
	@PostMapping(value = "/{jobName}/disable")
	public ApiResponse<Boolean> disableJob(@PathVariable("jobName") final String jobName) {
		jobAPIService.getJobOperatorAPI().disable(jobName, null);
		return ApiResponse.ok(Boolean.TRUE);
	}

	/**
	 * Enable job.
	 *
	 * @param jobName job name
	 */
	@PostMapping(value = "/{jobName}/enable")
	public ApiResponse<Boolean> enableJob(@PathVariable("jobName") final String jobName) {
		jobAPIService.getJobOperatorAPI().enable(jobName, null);
		return ApiResponse.ok(Boolean.TRUE);
	}

	/**
	 * Shutdown job.
	 *
	 * @param jobName job name
	 */
	@PostMapping(value = "/{jobName}/shutdown")
	public ApiResponse<Boolean> shutdownJob(@PathVariable("jobName") final String jobName) {
		jobAPIService.getJobOperatorAPI().shutdown(jobName, null);
		return ApiResponse.ok(Boolean.TRUE);
	}

	/**
	 * Get sharding info.
	 *
	 * @param jobName job name
	 * @return sharding info
	 */
	@GetMapping(value = "/{jobName}/sharding")
	public ApiResponse<Collection<ShardingInfo>> getShardingInfo(
		@PathVariable("jobName") final String jobName) {
		Collection<ShardingInfo> data =
			jobAPIService.getShardingStatisticsAPI().getShardingInfo(jobName);
		return ApiResponse.ok(data);
	}

	/**
	 * Disable sharding.
	 *
	 * @param jobName job name
	 * @param item    sharding item
	 */
	@PostMapping(value = "/{jobName}/sharding/{item}/disable")
	public ApiResponse<Boolean> disableSharding(
		@PathVariable("jobName") final String jobName, @PathVariable("item") final String item) {
		jobAPIService.getShardingOperateAPI().disable(jobName, item);
		return ApiResponse.ok(Boolean.TRUE);
	}

	/**
	 * Enable sharding.
	 *
	 * @param jobName job name
	 * @param item    sharding item
	 */
	@PostMapping(value = "/{jobName}/sharding/{item}/enable")
	public ApiResponse<Boolean> enableSharding(
		@PathVariable("jobName") final String jobName, @PathVariable("item") final String item) {
		jobAPIService.getShardingOperateAPI().enable(jobName, item);
		return ApiResponse.ok(Boolean.TRUE);
	}
}
