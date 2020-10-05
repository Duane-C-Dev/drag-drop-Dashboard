package com.draganddrop.Widget.controllers;

import com.draganddrop.Widget.api.WidgetDto;
import com.draganddrop.Widget.services.WidgetService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/widgets")
public class WidgetController {

    private final WidgetService widgetService;

    public WidgetController(WidgetService widgetService) {
        this.widgetService = widgetService;
    }

    @GetMapping
    public List<WidgetDto> findAll() {
        return widgetService.findAll();
    }

    @GetMapping("/{widgetId}")
    public WidgetDto find(@PathVariable("widgetId") UUID widgetId) {
        return widgetService.find(widgetId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody WidgetDto dto) {
        widgetService.create(dto);
    }

    @DeleteMapping("/{widgetId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("widgetId") UUID widgetId) {
        widgetService.delete(widgetId);
    }

    @PutMapping("/{widgetId}")
    public void update(@PathVariable("widgetId") UUID widgetId, @RequestBody WidgetDto dto) {
        widgetService.update(widgetId, dto);
    }
}
