package org.devops

class UserApproval {

    static void execute(script, environmentName) {

        script.timeout(time: 5, unit: 'MINUTES') {

            script.input(
                message: "Approve deployment to ${environmentName} ?",
                ok: "Deploy"
            )
        }
    }
}
