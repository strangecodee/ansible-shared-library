package org.devops

import java.util.Properties

class Config {

    static Map load(String filePath) {

        println "========== Loading Configuration =========="

        Properties props = new Properties()
        new File(filePath).withInputStream { stream ->
            props.load(stream)
        }

        return props as Map
    }
}
