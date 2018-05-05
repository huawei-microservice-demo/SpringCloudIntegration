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

package io.provider;

import javax.ws.rs.core.MediaType;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/hello", produces = MediaType.TEXT_PLAIN)
public class HelloService implements Hello {
  private static org.slf4j.Logger log = LoggerFactory.getLogger(HelloService.class);

  @Override
  @RequestMapping(path = "/sayhi", method = RequestMethod.GET)
  public String sayHi(@RequestParam(name = "name", required = false) String name) {
    log.info("Access /hello/sayhi, and name is " + name);
    return "from provider: Hello " + name;
  }
}
