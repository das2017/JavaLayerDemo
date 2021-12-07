package org.zzj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zzj.dto.common.ApiResponse;
import org.zzj.dto.common.PageResponse;
import org.zzj.dto.request.PayQueryRequest;
import org.zzj.dto.response.PayGetIDResponse;
import org.zzj.dto.response.PayQueryResponse;
import org.zzj.service.PayService;

import java.util.List;

@RestController
@RequestMapping("/pay")
@CrossOrigin
public class PayController extends BaseController {
    @Autowired
    private PayService service;

    @PostMapping("/query")
    public ApiResponse query(@RequestBody PayQueryRequest request) {
        PageResponse<PayQueryResponse> data = service.query(request);
        return success(data);
    }

    @GetMapping("/{id}")
    public ApiResponse GetID(@PathVariable String id) {
        List<PayGetIDResponse> data = service.getID(id);
        return success(data);
    }
}