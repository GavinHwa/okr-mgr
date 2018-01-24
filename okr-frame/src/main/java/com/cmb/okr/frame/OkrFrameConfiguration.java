package com.cmb.okr.frame;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ "com.cmb.okr.frame.config", "com.cmb.okr.frame.db", "com.cmb.okr.frame.util.bean" })
public class OkrFrameConfiguration {

}
