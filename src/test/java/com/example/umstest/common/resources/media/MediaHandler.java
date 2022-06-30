package com.example.umstest.common.resources.media;

import com.example.umstest.common.BaseHelper;
import com.example.umstest.common.enums.MediaEnum;
import com.example.umstest.common.enums.UserEnum;
import com.example.umstest.common.model.Media;
import com.example.umstest.common.model.User;
import com.example.umstest.common.resources.user.UserJson;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MediaHandler {
    private final List<MediaJson> mediaJsons;

    public MediaHandler()  {
        try {
            mediaJsons = Arrays.asList(BaseHelper.getJsonResourceData("data/media/media.json", MediaJson[].class));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public Media findByName(MediaEnum mediaEnum){
        return Objects.requireNonNull(mediaJsons
                        .stream()
                        .filter(mediaObj -> mediaObj.getId() == mediaEnum.getId())
                        .findFirst()
                        .orElse(null))
                .getData();
    }
}
