package com.example.umstest.common.resources.media;

import com.example.umstest.common.model.Media;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MediaJson {
    private int id;
    private String description;
    private Media data;
}
