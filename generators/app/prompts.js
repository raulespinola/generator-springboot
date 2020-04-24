
module.exports = {
    prompting
};

function prompting() {

    const done = this.async();

    const prompts = [
        {
            type: 'list',
            name: 'appType',
            message: 'You will use this Generator to create a SpringBoot application',
            choices: [
                {
                    value: 'microservice',
                    name: 'SpringBoot MicroService'
                }
            ],
            default: 'microservice'
        }
    ];

    this.prompt(prompts).then(answers => {
        Object.assign(this.configOptions, answers);
        done();
    });
}
