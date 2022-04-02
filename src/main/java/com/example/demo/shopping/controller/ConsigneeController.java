package com.example.demo.shopping.controller;

import com.example.demo.shopping.entity.Consignee;
import com.example.demo.shopping.service.ConsigneeService;
import com.example.demo.util.R;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consignee")
@AllArgsConstructor
@CrossOrigin
public class ConsigneeController {

    private final ConsigneeService consigneeService;

    @GetMapping("/list")
    @ResponseBody
    public R list(Long userId) {
        return consigneeService.list(userId);
    }

    @PostMapping("/add")
    @ResponseBody
    public R add(@RequestBody Consignee consignee) {
        return consigneeService.add(consignee);
    }

    @PostMapping("/delete")
    @ResponseBody
    public R delete(@RequestParam Long id) {
        return consigneeService.delete(id);
    }
}
