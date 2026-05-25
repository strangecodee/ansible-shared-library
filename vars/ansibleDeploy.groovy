import org.devops.CloneRepo
import org.devops.UserApproval
import org.devops.ExecutePlaybook
import org.devops.Notification

def call(Map config = [:]) {

    pipeline {

        agent any

        environment {

            REPO_URL            = "${config.REPO_URL}"
            BRANCH              = "${config.BRANCH ?: 'main'}"

            INVENTORY           = "${config.INVENTORY}"
            PLAYBOOK            = "${config.PLAYBOOK}"

            ENVIRONMENT_NAME    = "${config.ENVIRONMENT_NAME}"

            SLACK_CHANNEL_NAME  = "${config.SLACK_CHANNEL_NAME}"

            ACTION_MESSAGE      = "${config.ACTION_MESSAGE}"

            KEEP_APPROVAL_STAGE = "${config.KEEP_APPROVAL_STAGE ?: false}"

            EXTRA_ARGS          = "${config.EXTRA_ARGS ?: ''}"
        }

        stages {

            stage('Clone Repository') {

                steps {

                    script {

                        CloneRepo.execute(
                            this,
                            REPO_URL,
                            BRANCH
                        )
                    }
                }
            }

            stage('User Approval') {

                when {

                    expression {
                        return KEEP_APPROVAL_STAGE == "true"
                    }
                }

                steps {

                    script {

                        UserApproval.execute(
                            this,
                            ENVIRONMENT_NAME
                        )
                    }
                }
            }

            stage('Execute Ansible Playbook') {

                steps {

                    script {

                        ExecutePlaybook.execute(
                            this,
                            INVENTORY,
                            PLAYBOOK,
                            EXTRA_ARGS
                        )
                    }
                }
            }

            stage('Notification') {

                steps {

                    script {

                        Notification.execute(
                            this,
                            SLACK_CHANNEL_NAME,
                            ACTION_MESSAGE
                        )
                    }
                }
            }
        }
    }
}
