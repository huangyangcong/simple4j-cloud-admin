package com.simple4j.system.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MenuRoutersResponse {

  /** */
  private String path;

  private List<String> authorities;
}
