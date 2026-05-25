package org.devops

class Notification {

    static void execute(script, channel, message) {

        script.echo "========== Sending Slack Notification =========="

        script.slackSend(
            channel: channel,
            color: 'good',
            message: message
        )
    }
}
