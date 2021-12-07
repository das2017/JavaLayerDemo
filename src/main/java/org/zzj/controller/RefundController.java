package org.zzj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zzj.dto.common.ApiResponse;
import org.zzj.dto.common.PageResponse;
import org.zzj.dto.request.RefundCancelRequest;
import org.zzj.dto.request.RefundManualRequest;
import org.zzj.dto.request.RefundQueryRequest;
import org.zzj.dto.response.RefundGetIDResponse;
import org.zzj.dto.response.RefundQueryResponse;
import org.zzj.service.RefundSerivce;

import java.util.List;

@RestController
@RequestMapping("/refund")
@CrossOrigin
public class RefundController extends BaseController {
    @Autowired
    private RefundSerivce service;

    @PostMapping("/query")
    public ApiResponse query(@RequestBody RefundQueryRequest request) {
        PageResponse<RefundQueryResponse> data = service.query(request);
        return success(data);
    }

    @GetMapping("/{id}")
    public ApiResponse getID(@PathVariable String id) {
        List<RefundGetIDResponse> data = service.getID(id);
        return success(data);
    }

    @GetMapping("/manual")
    public ApiResponse manual(@RequestBody RefundManualRequest request) {
        service.manual(request);
        return success();
    }

    @GetMapping("cancel")
    public ApiResponse cancel(@RequestBody RefundCancelRequest request) {
        service.cancel(request);
        return success();
    }
}
