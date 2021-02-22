package com.htlabs.smartwatch.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/screen")
@Validated
@Slf4j
@Component
public class ScreenController extends BaseController{
}
