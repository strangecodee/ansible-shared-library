package org.devops

class ExecutePlaybook {

    static void execute(script, inventory, playbook, extraArgs = '') {

        script.echo "========== Running Ansible Playbook =========="

        script.sh """
        ANSIBLE_HOST_KEY_CHECKING=False ansible-playbook \
        -i ${inventory} \
        ${playbook} \
        -e "workspace=${script.env.WORKSPACE}" \
        ${extraArgs ?: ''}
        """
    }
}
