package org.devops

class ValidateEnvironment {

    static void execute(script) {

        script.echo "========== Validating Environment =========="

        script.sh '''

        ansible --version

        pwd

        ls -la

        '''
    }
}
