package org.devops

class ExecutePlaybook {

    static void execute(script, inventory, playbook, extraArgs) {

        script.echo "========== Running Ansible Playbook =========="

        script.sh """
        ansible-playbook \
        -i ${inventory} \
        ${playbook} \
        ${extraArgs}
        """
    }
}
