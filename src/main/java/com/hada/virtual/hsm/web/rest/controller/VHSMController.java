package com.hada.virtual.hsm.web.rest.controller;

import com.hada.virtual.hsm.domain.VHSMDevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hsm")
public class VHSMController {
    private static final Logger log = LoggerFactory.getLogger(VHSMController.class);

    @GetMapping("/check")
    public ResponseEntity<VHSMDevice> test() {

        VHSMDevice vhsmDevice = new VHSMDevice();

        return ResponseEntity.ok(vhsmDevice);
    }
}
