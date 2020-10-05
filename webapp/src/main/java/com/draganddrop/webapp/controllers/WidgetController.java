package com.draganddrop.webapp.controllers;


import com.draganddrop.webapp.clients.BFFClient;
import com.draganddrop.webapp.dto.WidgetDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/widgets")
public class WidgetController {

    private final BFFClient BFFClient;

    public WidgetController(BFFClient BFFClient) {
        this.BFFClient = BFFClient;
    }


    @GetMapping
    public List<WidgetDto> findAll() {
        return BFFClient.findAllWidget();
    }

    @GetMapping("/{widgetId}")
    public WidgetDto find(@PathVariable("widgetId") UUID widgetId) {
        return BFFClient.findWidget(widgetId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody WidgetDto dto) {
        BFFClient.createWidget(dto);
    }

    @DeleteMapping("/{widgetId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("widgetId") UUID widgetId) {
        BFFClient.deleteWidget(widgetId);
    }

    @PutMapping("/{widgetId}")
    public void update(@PathVariable("widgetId") UUID widgetId, @RequestBody WidgetDto dto) {
        BFFClient.updateWidget(widgetId, dto);
    }
}
