const HtmlReporter = require('protractor-beautiful-reporter');
const { SpecReporter } = require('jasmine-spec-reporter');
const jasmineReporters = require('jasmine-reporters');
const os = require('os');
const path = require('path');
const rimraf = require('rimraf');
const protractorDownloadPath = path.join(os.tmpdir(), 'protractor');

rimraf.sync(protractorDownloadPath);

exports.config = {
    allScriptsTimeout: 16000,
    directConnect: true,

    capabilities: {
        browserName: 'chrome',
        maxInstances: 1,
        chromeOptions: {
            args: [
                'lang=pt-BR',
                '--no-sandbox',
                '--disable-dev-shm-usage',
                '--window-size=1366x768',
                '--headless',
            ],
            prefs: {
                download: {
                    prompt_for_download: false,
                    default_directory: protractorDownloadPath,
                },
                intl: { accept_languages: 'pt-BR' },
            },
        },
    },

    onPrepare: function () {
        const path = require('path').join(__dirname, './Compartilhados/RelatorioE2E_' + getExecNum());

        require('ts-node').register({
            project: require('path').join(__dirname, './tsconfig.e2e.json'),
        });

        //organiza os resultados no Prompt de Comando
        jasmine.getEnv().addReporter(
            new SpecReporter({
                displayFailuresSummary: true,
                displayFailedSpec: true,
                displaySuiteNumber: true,
                displaySpecDuration: true,
            })
        );

        //Gera relatório HTML de resultados no diretório especificado
        jasmine.getEnv().addReporter(
            new HtmlReporter({
                preserveDirectory: true,
                baseDirectory: path,
                screenshotsSubfolder: 'images/',
                jsonsSubfolder: 'json/',
                takeScreenShotsOnlyForFailedSpecs: false,
                docTitle: 'Automação P&M ' + getDate(),
                docName: 'Automacao.html',
                excludeSkippedSpecs: false,
                clientDefaults: {
                    showTotalDurationIn: 'header',
                    totalDurationFormat: 'h:m:s',
                    columnSettings: {
                        warningTime: 20000,
                        dangerTime: 30000,
                    },
                },
            }).getJasmine2Reporter()
        );

        jasmine.getEnv().addReporter(
            new jasmineReporters.JUnitXmlReporter({
                consolidateAll: true,
                savePath: require('path').join(__dirname, './Compartilhados/junit/'),
                filePrefix: 'xmlresults',
                modifyReportFileName: function () {
                    return getDate();
                },
            })
        );

        const { browser } = require('protractor');
        return browser.getCapabilities().then((cap) => {
            browser.browserName = cap.get('browserName');
        });
    },

    framework: 'jasmine',
    jasmineNodeOpts: {
        showColors: true,
        defaultTimeoutInterval: 120000,
        print: function () { },
    },
};

function getDate() {
    let today = new Date();
    let dd = today.getDate();
    let mm = today.getMonth() + 1;
    const yyyy = today.getFullYear();
    const hh = today.getHours();
    const min = today.getMinutes();
    const sec = today.getSeconds();

    if (dd < 10) {
        dd = '0' + dd;
    }
    if (mm < 10) {
        mm = '0' + mm;
    }
    today = `${yyyy}${mm}${dd} ${hh}${min}${sec}`;
    return today;
}

function getExecNum() {
    var fs = require('fs');
    var route = require('path').join(__dirname, './Compartilhados');
    var files = fs.readdirSync(route);

    if (files && files.length >= 1) {
        return files.length + 1;
    } else {
        return 1;
    }
}
