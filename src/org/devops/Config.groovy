
package org.devops

class Config {

    static Map load(script, String fileName) {

        script.echo "========== Loading Configuration =========="

        return script.readProperties(
            file: fileName
        )
    }
}
