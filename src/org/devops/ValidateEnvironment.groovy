package org.devops

class ValidateEnvironment {

    static void execute(script) {

        script.echo "========== Validating Environment =========="

        script.sh '''

        export PATH=$PATH:/var/lib/jenkins/.local/bin
        ansible --version

        pwd

        ls -la

        '''
    }
}
