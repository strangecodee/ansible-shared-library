package org.devops

class CloneRepo {

    static void execute(script, repoUrl, branch) {

        script.echo "========== Cloning Repository =========="

        script.git(
            branch: branch,
            url: repoUrl
        )
    }
}
