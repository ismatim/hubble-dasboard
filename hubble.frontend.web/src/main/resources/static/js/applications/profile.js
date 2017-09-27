/* global _ */

(function () {
    'use strict';
    var applicationProfileKnobModule = (function () {

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
//                width: '100%', // responsive
                displayInput: true,
                fgColor: Colors.byName('success'),
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
                else if ($(knobSelected).attr('id').indexOf('performance') >= 0)
                    return configuration = $.extend({}, configuration, {
                        format: function (v) {
                            return v + "s";
                        }
                    });
                else if ($(knobSelected).attr('id').indexOf('incidencias') >= 0)
                    return configuration = $.extend({}, configuration, {
                        format: function (v) {
                            return v + "#";
                        }
                    });
                else if ($(knobSelected).attr('id').indexOf('tareas') >= 0)
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
                    knobSelected.trigger('configure', configuration);
                    knobSelected.trigger('change');
                    console.log(configuration);

                });
            }

            var timeOutId;
            $(window).resize(function () {
                clearTimeout(timeOutId); // The ID value of the timer returned by the setTimeout().
                setTimeout(doOnResizeWindows, 1 / 10);
            });

            $('#collapseOne').on('shown.bs.collapse', function () {
                clearTimeout(timeOutId);
                setTimeout(doOnResizeWindows, 2000);
            });

        };

        return {
            init: init
        };
    })();

    var applicationProfileChartModule = (function () {

        var init = function () {

            if (!$.fn.plot)
                return;

            // Dont run if charts page not loaded
            if (!$('#area-flotchart').length)
                return;

            $.get('/applicationasync/uptime/Benchmark%20Home%20Banking', function (data) {

                var areaData = [
                    {
                        data: [],
                        label: "Uptime",
                        color: "#06cb60"
                    }];

                data.forEach(function (element) {
                    return areaData[0].data.push([element["date"], element["uptime"]]);
                });

                var areaOptions = {
                    series: {
                        lines: {
                            show: true,
                            fill: true,
                            fillColor: {
                                colors: [{
                                        opacity: 0.5
                                    }, {
                                        opacity: 0.9
                                    }]
                            }
                        },
                        points: {
                            show: false
                        }
                    },
                    grid: {
                        borderColor: 'rgba(162,162,162,.26)',
                        borderWidth: 1,
                        hoverable: true,
                        backgroundColor: 'transparent'
                    },
                    tooltip: true,
                    tooltipOpts: {
                        content: function (label, x, y) {
                            return x + ' : ' + y;
                        }
                    },
                    xaxis: {
                        tickColor: 'rgba(162,162,162,.26)',
                        font: {
                            color: Colors.byName('blueGrey-200')
                        },
                        mode: 'categories'
                    },
                    yaxis: {
                        min: 0,
                        max: 150,
                        tickColor: 'rgba(162,162,162,.26)',
                        font: {
                            color: Colors.byName('blueGrey-200')
                        },
                        // position: (isRTL ? 'right' : 'left')
                    },
                    shadowSize: 0
                };

                $('#area-flotchart').plot(areaData, areaOptions);

            });

        };

        return {
            init: init
        };

    })();

    var applicationProfileMapsModule = (function () {

        var init = function () {
            'use strict';

            if (document.getElementById('map-markers')) {

                var mapMarkers = new GMaps({
                    div: '#map-markers',
                    lat: -32.9521399,
                    lng: -60.7681972
                });
                mapMarkers.addMarker({
                    lat: -32.9521399,
                    lng: -60.7681972,
                    title: 'Rosario',
                    infoWindow: {
                        content: '<p>Locación Rosario</p>'
                    }
                });
                mapMarkers.addMarker({
                    lat: -34.6131708,
                    lng: -58.3810633,
                    title: 'Buenos Aires',
                    infoWindow: {
                        content: '<p>Locación Buenos Aires</p>'
                    }
                });
            }
            mapMarkers.zoomOut(9);

        };

        return {
            init: init
        };

    })();


    $(applicationProfileKnobModule.init);
    $(applicationProfileChartModule.init);
    $(applicationProfileMapsModule.init);

})();


