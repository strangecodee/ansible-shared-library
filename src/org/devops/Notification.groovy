package org.devops

class Notification {

    static void execute(script, channel, message) {

        script.echo "========== Sending Slack Notification =========="

        def buildStatus = script.currentBuild.currentResult ?: "SUCCESS"

        def statusEmoji = buildStatus == "SUCCESS" ? ":white_check_mark:" : ":x:"

        def colorCode = buildStatus == "SUCCESS" ? "good" : "danger"

        script.slackSend(

            channel: channel,

            color: colorCode,

            message: """
${statusEmoji} *DEPLOYMENT NOTIFICATION*

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

:bookmark_tabs: *Application Message*
${message}

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

:hammer_and_wrench: *Job Name:* ${script.env.JOB_NAME}

:1234: *Build Number:* #${script.env.BUILD_NUMBER}

:bar_chart: *Build Status:* ${buildStatus}

:git_branch: *Branch:* ${script.env.BRANCH_NAME ?: 'main'}

:bust_in_silhouette: *Triggered By:* ${script.currentBuild.getBuildCauses()[0].shortDescription}

:link: *Build URL:* 
${script.env.BUILD_URL}

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

:rocket: Jenkins Shared Library Deployment
"""
        )
    }
}
