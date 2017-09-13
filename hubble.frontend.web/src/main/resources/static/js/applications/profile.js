/* global _ */

(function () {
    'use strict';
    var applicationProfileModule = (function () {

        var chartAvailability10min = $('#chart-disponibilidad-10-min');
        var chartPerformance10min = $('#chart-performance-10-min');
        var chartAvailability1hour = $('#chart-disponibilidad-1-hora');
        var chartPerformance1hour = $('#chart-performance-1-hora');
        var chartAvailability1day = $('#chart-disponibilidad-1-day');
        var chartPerformance1day = $('#chart-performance-1-day');
        var chartTasks1day = $('#chart-tareas-1-day');
        var chartIncidences1day = $('#chart-incidencias-1-day');

        var knobs = [chartAvailability10min, chartPerformance10min,
            chartAvailability1hour, chartPerformance1hour,
            chartAvailability1day, chartPerformance1day, chartTasks1day, chartIncidences1day];

        var init = function () {

            if (!$.fn.plot || !$.fn.knob)
                return;

            var knobLoaderOptionsSuccess = {
                width: '80%', // responsive
                displayInput: true,
                fgColor: Colors.byName('primary'),
                bgColor: 'rgba(162,162,162, .09)',
                angleOffset: -125,
                angleArc: 250,
                readOnly: true,
                step: 0.01
            };
            var knobLoaderOptionsDanger = $.extend({}, knobLoaderOptionsSuccess, {
                fgColor: Colors.byName('danger')
            });

            var knobLoaderOptionsWarning = $.extend({}, knobLoaderOptionsSuccess, {
                fgColor: Colors.byName('warning')
            });

            var knobLoaderOptionsNoData = $.extend({}, knobLoaderOptionsSuccess, {
                displayInput: false
            });

            var setFormat = function (knobSelected, configuration) {
                if ($(knobSelected).attr('id').indexOf('disponibilidad') >= 0)
                    return configuration = $.extend({}, configuration, {
                        format: function (v) {
                            return v + "%";
                        }
                    });
                else if($(knobSelected).attr('id').indexOf('performance') >= 0)
                    return configuration = $.extend({}, configuration, {
                        format: function (v) {
                            return v + "s";
                        }
                    });
                else if($(knobSelected).attr('id').indexOf('incidencias') >= 0)
                    return configuration = $.extend({}, configuration, {
                        format: function (v) {
                            return v + "#";
                        }
                    });
                else if($(knobSelected).attr('id').indexOf('tareas') >= 0)
                    return configuration = $.extend({}, configuration, {
                        format: function (v) {
                            return v + "#";
                        }
                    });
            };

            var getKnobStatus = function (status) {
                if (status === 'Success')
                    return knobLoaderOptionsSuccess;
                else if (status === 'Warning')
                    return knobLoaderOptionsWarning;
                else if (status === 'Critical')
                    return knobLoaderOptionsDanger;
                else if (status === 'No_Data')
                    return knobLoaderOptionsNoData;
            };

            var getChartStatus = function (knobSelected) {
                return knobSelected.data("status");
            };

            $.each(knobs, function (index, knobSelected) {
                //init
                var configuration = getKnobStatus(getChartStatus(knobSelected));
                configuration = setFormat(knobSelected, configuration);
                knobSelected.knob(configuration);

            });

            function doOnResizeWindows() {
                $.each(knobs, function (index, knobSelected) {
                    //init
                    var configuration = getKnobStatus(getChartStatus(knobSelected));
                    configuration = setFormat(knobSelected, configuration);
                    knobSelected.trigger('configure', configuration );
                    knobSelected.trigger('change');
                    console.log(configuration);

                });
            }

            var timeOutId;
            $(window).resize(function () {
                clearTimeout(timeOutId); // The ID value of the timer returned by the setTimeout().
                setTimeout(doOnResizeWindows, 1/10);
            });

        };

        return {
            init: init
        };
    })();

    $(applicationProfileModule.init);

})();
