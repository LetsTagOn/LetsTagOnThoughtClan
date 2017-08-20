var localization = angular.module('localization', [
        'ngLocalize',
        'ngLocalize.Config',
        'ngLocalize.InstalledLanguages'
    ]).value('localeConf', {
        basePath: 'languages',
        defaultLocale: 'en-US',
        sharedDictionary: 'common',
        fileExtension: '.lang.json',
        persistSelection: true,
        cookieName: 'COOKIE_LOCALE_LANG',
        observableAttrs: new RegExp('^data-(?!ng-|i18n)'),
        delimiter: '::',
        validTokens: new RegExp('^[\\w\\.-]+\\.[\\w\\s\\.-]+\\w(:.*)?$')
    }).value('localeSupported', [
        'en-US'

    ])
    .value('localeFallbacks', {
        'en': 'en-US'

    });
localization.controller('controller', ['$scope', 'localeEvents',
    function($scope, localeEvents) {
        $scope.$on(localeEvents.resourceUpdates, function(data) {
            // Example data parameter for fr-FR common.lang.json bundle:
            // {
            //     locale: 'en-IN',
            //     path: 'common',
            //     bundle: {
            //         "User": "Volunteer",
            //"Connections": "Connection"
            //     }
            // }

        });
        $scope.$on(localeEvents.localeChanges, function(event, data) {
            console.log('new locale chosen: ' + data);
        });
    }
]);