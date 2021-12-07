package org.zzj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import org.zzj.common.constants.ApiCode;
import org.zzj.dto.common.*;
import org.zzj.dto.request.ChannelModifyRequest;
import org.zzj.dto.request.ChannelQueryRequest;
import org.zzj.dto.response.ChannelQueryResponse;
import org.zzj.service.ChannelService;

@RestController
@RequestMapping("/channel")
@CrossOrigin
public class ChannelController extends BaseController {
    @Autowired
    private ChannelService service;

    @PostMapping("/add")
    public ApiResponse add(@RequestBody ChannelModifyRequest request) {
        service.add(request);
        return success(ApiCode.OKMSG_ADD);
    }

    @PostMapping("/update")
    public ApiResponse update(@RequestBody ChannelModifyRequest request) {
        service.update(request);
        return success(ApiCode.OKMSG_UPDATE);
    }

    @GetMapping("/{id}")
    public ApiResponse findById(@PathVariable Integer id) {
        ChannelQueryResponse data = service.findById(id);
        return success(data);
    }

    @GetMapping("/list")
    public ApiResponse findAll() {
        List<ChannelQueryResponse> data = service.findAll();
        return success(data);
    }

    @PostMapping("/query")
    public ApiResponse query(@RequestBody ChannelQueryRequest request) {
        PageResponse<ChannelQueryResponse> data = service.query(request);
        return success(data);
    }
}