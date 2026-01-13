package com.factory.machine.events.dto;

import java.util.ArrayList;
import java.util.List;

public class BatchResponse {
    public int accepted;
    public int deduped;
    public int updated;
    public int rejected;
    public List<Rejection> rejections = new ArrayList<>();
}
