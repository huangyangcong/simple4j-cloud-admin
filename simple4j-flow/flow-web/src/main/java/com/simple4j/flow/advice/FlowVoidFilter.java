package com.simple4j.flow.advice;

import cn.hutool.json.JSONUtil;
import com.simple4j.web.bean.ApiResponse;
import org.springframework.http.HttpStatus;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class FlowVoidFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		chain.doFilter(request, response);
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		if (HttpStatus.NO_CONTENT.value() == httpServletResponse.getStatus()) {
			httpServletResponse.setStatus(HttpStatus.OK.value());
			response.setCharacterEncoding(StandardCharsets.UTF_8.name());
			PrintWriter writer = httpServletResponse.getWriter();
			writer.write(JSONUtil.toJsonStr(ApiResponse.ok()));
		}
	}

}
