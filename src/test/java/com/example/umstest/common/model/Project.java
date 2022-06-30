package com.example.umstest.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
        private String id;
        private String name;
        private String image;
        private boolean active;

        public Project(String name) {
                this.name = name;
        }
}
