/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

if (!d3) {
    d3 = {};
}

var ig = {};
function incomeGraph(element, height) {
    ig.d3Container = d3.select(element);
    ig.margin = {top: 5, right: 50, bottom: 40, left: 50};
    ig.width = ig.d3Container.node().getBoundingClientRect().width - ig.margin.left - ig.margin.right;
    ig.height = height - ig.margin.top - ig.margin.bottom;
    ig.tooltipOffset = 30;

    ig.tooltip = ig.d3Container
            .append("div")
            .attr("class", "d3-tip e")
            .style("display", "none");

    // Format date
    ig.format = d3.time.format("%m/%d/%y %H:%M");
    ig.formatDate = d3.time.format("%H:%M");

    // Colors
    ig.colorrange = ['#03A9F4', '#29B6F6', '#4FC3F7', '#81D4FA', '#B3E5FC', '#E1F5FE'];



    // Construct scales
    // ------------------------------

    // Horizontal
    ig.x = d3.time.scale().range([0, ig.width]);

    // Vertical
    ig.y = d3.scale.linear().range([ig.height, 0]);

    // Colors
    ig.z = d3.scale.ordinal().range(ig.colorrange);



    // Create axes
    // ------------------------------

    // Horizontal
    ig.xAxis = d3.svg.axis()
            .scale(ig.x)
            .orient("bottom")
            .ticks(d3.time.hours, 4)
            .innerTickSize(4)
            .tickPadding(8)
            .tickFormat(d3.time.format("%H:%M")); // Display hours and minutes in 24h format

    // Left vertical
    ig.yAxis = d3.svg.axis()
            .scale(ig.y)
            .ticks(6)
            .innerTickSize(4)
            .outerTickSize(0)
            .tickPadding(8)
            .tickFormat(function (d) {
                return (d / 1000) + "k";
            });

    // Right vertical
    ig.yAxis2 = ig.yAxis;

    // Dash lines
    ig.gridAxis = d3.svg.axis()
            .scale(ig.y)
            .orient("left")
            .ticks(6)
            .tickPadding(8)
            .tickFormat("")
            .tickSize(-ig.width, 0, 0);



    // Create chart
    // ------------------------------

    // Container
    ig.container = ig.d3Container.append("svg");

    // SVG element
    ig.svg = ig.container
            .attr('width', ig.width + ig.margin.left + ig.margin.right)
            .attr("height", height + ig.margin.top + ig.margin.bottom)
            .append("g")
            .attr("transform", "translate(" + ig.margin.left + "," + ig.margin.top + ")");



    // Construct chart layout
    // ------------------------------

    // Stack
    ig.stack = d3.layout.stack()
//                    .offset("silhouette")
            .values(function (d) {
                return d.values;
            })
            .x(function (d) {
                return d.date;
            })
            .y(function (d) {
                return d.value;
            });

    // Nest
    ig.nest = d3.nest()
            .key(function (d) {
                return d.key;
            });

    // Area
    ig.area = d3.svg.area()
            .interpolate("cardinal")
            .x(function (d) {
                return ig.x(d.date);
            })
            .y0(function (d) {
                return ig.y(d.y0);
            })
            .y1(function (d) {
                return ig.y(d.y0 + d.y);
            });

    $(window).on('resize', resizeStream);
    $(document).on('click', '.sidebar-control', resizeStream);
    function resizeStream() {
        ig.width = ig.d3Container.node().getBoundingClientRect().width - ig.margin.left - ig.margin.right;
        ig.container.attr("width", ig.width + ig.margin.left + ig.margin.right);
        ig.svg.attr("width", ig.width + ig.margin.left + ig.margin.right);
        ig.x.range([0, ig.width]);


        ig.svg.selectAll('.d3-axis-horizontal').call(ig.xAxis);
        ig.svg.selectAll('.d3-axis-subticks').attr("x1", ig.x).attr("x2", ig.x);
        ig.svg.selectAll(".d3-grid-dashed").call(ig.gridAxis.tickSize(-ig.width, 0, 0));
        ig.svg.selectAll(".d3-axis-right").attr("transform", "translate(" + ig.width + ", 0)");
        ig.svg.selectAll('.streamgraph-layer').attr("d", function (d) {
            return ig.area(d.values);
        });
    }
}

function updateIncomeGraph(data) {
    data.forEach(function (d) {
        d.date = ig.format.parse(d.date);
        d.value = +d.value;
    });

    ig.data = data;
}

function initIncomeGraph(data) {
    // Pull out values
    data.forEach(function (d) {
        d.date = ig.format.parse(d.date);
        d.value = +d.value;
    });


    // Stack and nest layers
    var layers = ig.stack(ig.nest.entries(data));


    // Set input domains
    // ------------------------------

    // Horizontal
    ig.x.domain(d3.extent(data, function (d, i) {
        return d.date;
    }));

    // Vertical
    ig.y.domain([0, d3.max(data, function (d) {
            return d.y0 + d.y;
        })]);



    // Add grid
    // ------------------------------
    ig.svg.html("");
    // Horizontal grid. Must be before the group
    ig.svg.append("g")
            .attr("class", "d3-grid-dashed")
            .call(ig.gridAxis);



    ig.data = data;

    //
    // Append chart elements
    //

    // Stream layers
    // ------------------------------

    // Create group
    ig.group = ig.svg.append('g')
            .attr('class', 'streamgraph-layers-group');

    // And append paths to this group
    ig.layer = ig.group.selectAll(".streamgraph-layer")
            .data(layers)
            .enter()
            .append("path")
            .attr("class", "streamgraph-layer")
            .attr("d", function (d) {
                return ig.area(d.values);
            })
            .style('stroke', '#fff')
            .style('stroke-width', 0.5)
            .style("fill", function (d, i) {
                return ig.z(i);
            });

    // Add transition
    ig.layerTransition = ig.layer
            .style('opacity', 0)
            .transition()
            .duration(750)
            .delay(function (d, i) {
                return i * 50;
            })
            .style('opacity', 1);



    // Append axes
    // ------------------------------

    //
    // Left vertical
    //

    ig.svg.append("g")
            .attr("class", "d3-axis d3-axis-left d3-axis-solid")
            .call(ig.yAxis.orient("left"));

    // Hide first tick
    d3.select(ig.svg.selectAll('.d3-axis-left .tick text')[0][0])
            .style("visibility", "hidden");


    //
    // Right vertical
    //

    ig.svg.append("g")
            .attr("class", "d3-axis d3-axis-right d3-axis-solid")
            .attr("transform", "translate(" + ig.width + ", 0)")
            .call(ig.yAxis2.orient("right"));

    // Hide first tick
    d3.select(ig.svg.selectAll('.d3-axis-right .tick text')[0][0])
            .style("visibility", "hidden");


    //
    // Horizontal
    //

    ig.xaxisg = ig.svg.append("g")
            .attr("class", "d3-axis d3-axis-horizontal d3-axis-solid")
            .attr("transform", "translate(-10," + ig.height + ")")
            .call(ig.xAxis);

    // Add extra subticks for hidden hours
//    ig.xaxisg.selectAll(".d3-axis-subticks")
//            .data(ig.x.ticks(d3.time.hours), function (d) {
//                return d;
//            })
//            .enter()
//            .append("line")
//            .attr("transform", "translate(" + -10 + ",0)")
//            .attr("class", "d3-axis-subticks")
//            .attr("y1", 0)
//            .attr("y2", 4)
//            .attr("x1", ig.x)
//            .attr("x2", ig.x);



    // Add hover line and pointer
    // ------------------------------

    // Append group to the group of paths to prevent appearance outside chart area
    ig.hoverLineGroup = ig.group.append("g")
            .attr("class", "hover-line");

    // Add line
    ig.hoverLine = ig.hoverLineGroup
            .append("line")
            .attr("y1", 0)
            .attr("y2", ig.height)
            .style('fill', 'none')
            .style('stroke', '#fff')
            .style('stroke-width', 1)
            .style('pointer-events', 'none')
            .style('shape-rendering', 'crispEdges')
            .style("opacity", 0);

    // Add pointer
    ig.hoverPointer = ig.hoverLineGroup
            .append("rect")
            .attr("x", 2)
            .attr("y", 2)
            .attr("width", 6)
            .attr("height", 6)
            .style('fill', '#03A9F4')
            .style('stroke', '#fff')
            .style('stroke-width', 1)
            .style('shape-rendering', 'crispEdges')
            .style('pointer-events', 'none')
            .style("opacity", 0);



    // Append events to the layers group
    // ------------------------------

    ig.layerTransition.each("end", function () {
        ig.layer
                .on("mouseover", function (d, i) {
                    ig.svg.selectAll(".streamgraph-layer")
                            .transition()
                            .duration(250)
                            .style("opacity", function (d, j) {
                                return j !== i ? 0.75 : 1; // Mute all except hovered
                            });
                })

                .on("mousemove", function (d, i) {
                    ig.mouse = d3.mouse(this);
                    ig.mousex = ig.mouse[0];
                    ig.mousey = ig.mouse[1];
                    ig.datearray = [];
                    var invertedx = ig.x.invert(ig.mousex);
                    invertedx = invertedx.getHours();
                    var selected = (d.values);
                    for (var k = 0; k < selected.length; k++) {
                        ig.datearray[k] = selected[k].date;
                        ig.datearray[k] = ig.datearray[k].getHours();
                    }
                    ig.mousedate = ig.datearray.indexOf(invertedx);
                    ig.pro = d.values[ig.mousedate].value;


                    // Display mouse pointer
                    ig.hoverPointer
                            .attr("x", ig.mousex - 3)
                            .attr("y", ig.mousey - 6)
                            .style("opacity", 1);

                    ig.hoverLine
                            .attr("x1", ig.mousex)
                            .attr("x2", ig.mousex)
                            .style("opacity", 1);

                    //
                    // Tooltip
                    //

                    // Tooltip data
                    ig.tooltip.html(
                            "<ul class='list-unstyled mb-5'>" +
                            "<li>" + "<div class='text-size-base mt-5 mb-5'><i class='icon-circle-left2 position-left'></i>" + d.key + "</div>" + "</li>" +
                            "<li>" + "Visits: &nbsp;" + "<span class='text-semibold pull-right'>" + ig.pro + "</span>" + "</li>" +
                            "<li>" + "Time: &nbsp; " + "<span class='text-semibold pull-right'>" + ig.formatDate(d.values[ig.mousedate].date) + "</span>" + "</li>" +
                            "</ul>"
                            )
                            .style("display", "block");

                    // Tooltip arrow
                    ig.tooltip.append('div').attr('class', 'd3-tip-arrow');
                })

                .on("mouseout", function (d, i) {

                    // Revert full opacity to all paths
                    ig.svg.selectAll(".streamgraph-layer")
                            .transition()
                            .duration(250)
                            .style("opacity", 1);

                    // Hide cursor pointer
                    ig.hoverPointer.style("opacity", 0);

                    // Hide tooltip
                    ig.tooltip.style("display", "none");

                    ig.hoverLine.style("opacity", 0);
                });
    });



    // Append events to the chart container
    // ------------------------------

    ig.d3Container
            .on("mousemove", function (d, i) {
                ig.mouse = d3.mouse(this);
                ig.mousex = ig.mouse[0];
                ig.mousey = ig.mouse[1];

                // Display hover line
                //.style("opacity", 1);


                // Move tooltip vertically
                ig.tooltip.style("top", (ig.mousey - ($('.d3-tip').outerHeight() / 2)) - 2 + "px") // Half tooltip height - half arrow width

                // Move tooltip horizontally
                if (ig.mousex >= ($(ig.element).outerWidth() - $('.d3-tip').outerWidth() - ig.margin.right - (ig.tooltipOffset * 2))) {
                    ig.tooltip
                            .style("left", (ig.mousex - $('.d3-tip').outerWidth() - ig.tooltipOffset) + "px") // Change tooltip direction from right to left to keep it inside graph area
                            .attr("class", "d3-tip w");
                } else {
                    ig.tooltip
                            .style("left", (ig.mousex + ig.tooltipOffset) + "px")
                            .attr("class", "d3-tip e");
                }
            });
}


// Initialize chart

// Chart setup
function branchProductivityBar(element, height, data, name) {


    // Basic setup
    // ------------------------------

    // Define main variables
    var d3Container = d3.select(element),
            margin = {top: 5, right: 20, bottom: 20, left: 40},
            width = d3Container.node().getBoundingClientRect().width - margin.left - margin.right,
            height = height - margin.top - margin.bottom - 5;

    // Add data
    
    var index = d3.range(data.length);
            



    // Construct scales
    // ------------------------------

    // Horizontal
    var x = d3.scale.linear()
            .domain([0, d3.max(data)])
            .range([0, width]);

    // Vertical
    var y = d3.scale.ordinal()
            .domain(index)
            .rangeRoundBands([height, 0], .3);

    // Colors
    var colors = d3.scale.category20c();



    // Create axes
    // ------------------------------

    // Horizontal
    var xAxis = d3.svg.axis()
            .scale(x)
            .orient("bottom");

    // Vertical
    var yAxis = d3.svg.axis()
            .scale(y)
            .orient("left")
            .ticks(8);



    // Create chart
    // ------------------------------

    // Add SVG element
    var container = d3Container.append("svg");

    // Add SVG group
    var svg = container
            .attr("width", width + margin.left + margin.right)
            .attr("height", height + margin.top + margin.bottom)
            .append("g")
            .attr("transform", "translate(" + margin.left + "," + margin.top + ")");


    //
    // Append chart elements
    //

    // Append axes
    // ------------------------------

    // Horizontal
    svg.append("g")
            .attr("class", "d3-axis d3-axis-horizontal d3-axis-strong")
            .attr("transform", "translate(0," + height + ")")
            .call(xAxis);

    // Vertical
    svg.append("g")
            .attr("class", "d3-axis d3-axis-vertical d3-axis-strong")
            .call(yAxis);


    // Append bars
    // ------------------------------

    // Group each bar
    var bar = svg.selectAll(".d3-bar")
            .data(data)
            .enter()
            .append("g")
            .attr("class", "d3-bar")
            .attr("fill", function (d, i) {
                return colors(i);
            })
            .attr("transform", function (d, i) {
                return "translate(0," + y(i) + ")";
            });

    // Append bar rectangle
    bar.append("rect")
            .attr("height", y.rangeBand())
            .attr("width", x);

    // Append text label
    bar.append("text")
            .attr("x", function (d) {
                return x(d) - 12;
            })
            .attr("y", y.rangeBand() / 2)
            .attr("dy", ".35em")
            .style("fill", "#fff")
            .style("text-anchor", "end")
            .text(function (d, i) {
                return name[i] + " " + Math.floor(d) + "%";
            });


    // Setup sort
    // ------------------------------
//
//    window.sort = true;
//    setInterval(function () {
//        if (window.sort) {
//            index.sort(function (a, b) {
//                return data[a] - data[b];
//            });
//        } else {
//            index = d3.range(8);
//        }
//
//        y.domain(index);
//
//        bar.transition()
//                .duration(750)
//                .delay(function (d, i) {
//                    return i * 50;
//                })
//                .attr("transform", function (d, i) {
//                    return "translate(0," + y(i) + ")";
//                });
//    }, 4000);


    index.sort(function (a, b) {
        return data[a] - data[b];
    });

    y.domain(index);

    bar.transition()
            .duration(750)
            .delay(function (d, i) {
                return i * 50;
            })
            .attr("transform", function (d, i) {
                return "translate(0," + y(i) + ")";
            });
    // Resize chart
    // ------------------------------

    // Call function on window resize
    $(window).on('resize', resize);

    // Call function on sidebar width change
    $('.sidebar-control').on('click', resize);

    // Resize function
    // 
    // Since D3 doesn't support SVG resize by default,
    // we need to manually specify parts of the graph that need to 
    // be updated on window resize
    function resize() {

        // Layout variables
        width = d3Container.node().getBoundingClientRect().width - margin.left - margin.right;


        // Layout
        // -------------------------

        // Main svg width
        container.attr("width", width + margin.left + margin.right);

        // Width of appended group
        svg.attr("width", width + margin.left + margin.right);


        // Axes
        // -------------------------

        // Horizontal range
        x.range([0, width]);

        // Horizontal axis
        svg.selectAll('.d3-axis-horizontal').call(xAxis);


        // Chart elements
        // -------------------------

        // Bar rect
        svg.selectAll('.d3-bar rect').attr("width", x);

        // Bar text
        svg.selectAll('.d3-bar text').attr("x", function (d) {
            return x(d) - 12;
        });
    }
}


// Rounded progress - multiple arcs
// ------------------------------

// Initialize chart

// Chart setup
function roundedProgressMultiple(element, size) {

    // Add random data
    var data = [
        {index: 0, name: 'Memory', percentage: 0},
        {index: 1, name: 'CPU', percentage: 0},
        {index: 2, name: 'Sessions', percentage: 0},
        {index: 3, name: 'aaSessions', percentage: 0}
    ];

    // Main variables
    var d3Container = d3.select(element),
            padding = 2,
            strokeWidth = 8,
            width = size,
            height = size,
            pi2 = 2 * Math.PI;

    // Colors
    var colors = ['#78909C', '#F06292', '#4DB6AC'];


    // Create chart
    // ------------------------------

    // Add svg element
    var container = d3Container.append("svg");

    // Add SVG group
    var svg = container
            .attr("width", width)
            .attr("height", height)
            .append("g")
            .attr("transform", "translate(" + width / 2 + "," + height / 2 + ")");


    // Construct chart layout
    // ------------------------------

    // Foreground arc
    var arc = d3.svg.arc()
            .startAngle(0)
            .endAngle(function (d) {
                return d.percentage / 100 * pi2;
            })
            .innerRadius(function (d) {
                return (size / 2) - d.index * (strokeWidth + padding);
            })
            .outerRadius(function (d) {
                return ((size / 2) - d.index * (strokeWidth + padding)) - strokeWidth;
            })
            .cornerRadius(20);

    // Background arc
    var background = d3.svg.arc()
            .startAngle(0)
            .endAngle(pi2)
            .innerRadius(function (d) {
                return (size / 2) - d.index * (strokeWidth + padding);
            })
            .outerRadius(function (d) {
                return ((size / 2) - d.index * (strokeWidth + padding)) - strokeWidth;
            });


    // Append chart elements
    // ------------------------------

    //
    // Group arc elements
    //

    // Group
    var field = svg.selectAll("g")
            .data(data)
            .enter().append("g");

    // Foreground arcs
    field
            .append("path")
            .attr("class", "arc-foreground")
            .style("fill", function (d, i) {
                return colors[i];
            });

    // Background arcs
    field
            .append("path")
            .style("fill", function (d, i) {
                return colors[i];
            })
            .style("opacity", 0.1)
            .attr("d", background);


    //
    // Add legend
    //

    // Append list
    var legend = d3.select(element)
            .append('ul')
            .attr('class', 'chart-widget-legend text-muted')
            .selectAll('li')
            .data(data)
            .enter()
            .append('li')
            .attr('data-slice', function (d, i) {
                return i;
            })
            .attr('style', function (d, i) {
                return 'border-bottom: solid 2px ' + colors[i];
            })
            .text(function (d, i) {
                return d.name;
            });


    //
    // Animate elements
    //

    // Add transition
    d3.transition().each(update);

    // Animation
    function update() {
        field = field
                .each(function (d) {
                    this._value = d.percentage;
                })
                .data(data)
                .each(function (d) {
                    d.previousValue = this._value;
                    d.percentage = Math.round(Math.random() * 100) + 1;
                });

        // Foreground arcs
        field
                .select("path.arc-foreground")
                .transition()
                .duration(750)
                .ease("easeInOut")
                .attrTween("d", arcTween);

        // Update every 4 seconds
        setTimeout(update, 4000);
    }

    // Arc animation
    function arcTween(d) {
        var i = d3.interpolateNumber(d.previousValue, d.percentage);
        return function (t) {
            d.percentage = i(t);
            return arc(d);
        };
    }
}

// App sales lines chart
// ------------------------------


var csl = {};
// Chart setup
function customerSatisfactionLine(element, height) {


    // Basic setup
    // ------------------------------

    // Define main variables
    var d3Container = d3.select(element),
            margin = {top: 5, right: 30, bottom: 30, left: 50},
            width = d3Container.node().getBoundingClientRect().width - margin.left - margin.right,
            height = height - margin.top - margin.bottom;

    // Tooltip
    var tooltip = d3.tip()
            .attr('class', 'd3-tip')
            .html(function (d) {
                return "<ul class='list-unstyled mb-5'>" +
                        "<li>" + "<div class='text-size-base mt-5 mb-5'>Age Group <br> " + d.name + "</div>" + "</li>" +
                        "<li>" + "Satisfaction: &nbsp;" + "<span class='text-semibold pull-right'>" + d.value + "%</span>" + "</li>" +
                        "</ul>";
            });

    // Format date
    var parseDate = d3.time.format("%Y/%m/%d").parse,
            formatDate = d3.time.format("%b %d, '%y");

    // Line colors
    var scale = ["#4CAF50", "#FF5722", "#5C6BC0"],
            color = d3.scale.ordinal().range(scale);



    // Create chart
    // ------------------------------

    // Container
    var container = d3Container.append('svg');

    // SVG element
    var svg = container
            .attr('width', width + margin.left + margin.right)
            .attr('height', height + margin.top + margin.bottom)
            .append("g")
            .attr("transform", "translate(" + margin.left + "," + margin.top + ")")
            .call(tooltip);




    // Load data
    // ------------------------------


    // Construct layout
    // ------------------------------



    // Main chart drawing function
    // ------------------------------

    csl.redraw = function () {

        // Construct chart layout
        // ------------------------------

        // Create data nests
        var nested = d3.nest()
                .key(function (d) {
                    return d.type;
                }).map(csl.formatted);

        // Get value from menu selection
        // the option values correspond
        //to the [type] value we used to nest the data  
        var series = "val3";
        // Only retrieve data from the selected series using nest
        var data = nested[series];

        // For object constancy we will need to set "keys", one for each type of data (column name) exclude all others.
        color.domain(d3.keys(data[0]).filter(function (key) {
            return (key !== "date" && key !== "type");
        }));

        // Setting up color map
        var linedata = color.domain().map(function (name) {
            return {
                name: name,
                values: data.map(function (d) {
                    return {name: name, date: parseDate(d.date), value: parseFloat(d[name], 10)};
                })
            };
        });

        // Draw the line
        var line = d3.svg.line()
                .x(function (d) {
                    return x(d.date);
                })
                .y(function (d) {
                    return y(d.value);
                })
                .interpolate('cardinal');



        // Construct scales
        // ------------------------------

        // Horizontal
        var x = d3.time.scale()
                .domain([
                    d3.min(linedata, function (c) {
                        return d3.min(c.values, function (v) {
                            return v.date;
                        });
                    }),
                    d3.max(linedata, function (c) {
                        return d3.max(c.values, function (v) {
                            return v.date;
                        });
                    })
                ])
                .range([0, width]);

        // Vertical
        var y = d3.scale.linear()
                .domain([
                    d3.min(linedata, function (c) {
                        return d3.min(c.values, function (v) {
                            return v.value;
                        });
                    }),
                    d3.max(linedata, function (c) {
                        return d3.max(c.values, function (v) {
                            return v.value;
                        });
                    })
                ])
                .range([height, 0]);



        // Create axes
        // ------------------------------

        // Horizontal
        var xAxis = d3.svg.axis()
                .scale(x)
                .orient("bottom")
                .tickPadding(8)
                .ticks(d3.time.days)
                .innerTickSize(4)
                .tickFormat(d3.time.format("%m/%d")); // Display hours and minutes in 24h format

        // Vertical
        var yAxis = d3.svg.axis()
                .scale(y)
                .orient("left")
                .ticks(6)
                .tickSize(0 - width)
                .tickPadding(8);



        //
        // Append chart elements
        //

        // Append axes
        // ------------------------------

        // Horizontal
        svg.append("g")
                .attr("class", "d3-axis d3-axis-horizontal d3-axis-solid")
                .attr("transform", "translate(0," + height + ")");

        // Vertical
        svg.append("g")
                .attr("class", "d3-axis d3-axis-vertical d3-axis-transparent");



        // Append lines
        // ------------------------------

        // Bind the data
        var lines = svg.selectAll(".lines")
                .data(linedata)

        // Append a group tag for each line
        var lineGroup = lines
                .enter()
                .append("g")
                .attr("class", "lines")
                .attr('id', function (d) {
                    return d.name + "-line";
                });

        // Append the line to the graph
        lineGroup.append("path")
                .attr("class", "d3-line d3-line-medium")
                .style("stroke", function (d) {
                    return color(d.name);
                })
                .style('opacity', 0)
                .attr("d", function (d) {
                    return line(d.values[0]);
                })
                .transition()
                .duration(500)
                .delay(function (d, i) {
                    return i * 200;
                })
                .style('opacity', 1);



        // Append circles
        // ------------------------------

        var circles = lines.selectAll("circle")
                .data(function (d) {
                    return d.values;
                })
                .enter()
                .append("circle")
                .attr("class", "d3-line-circle d3-line-circle-medium")
                .attr("cx", function (d, i) {
                    return x(d.date)
                })
                .attr("cy", function (d, i) {
                    return y(d.value)
                })
                .attr("r", 3)
                .style('fill', '#fff')
                .style("stroke", function (d) {
                    return color(d.name);
                });

        // Add transition
        circles
                .style('opacity', 0)
                .transition()
                .duration(500)
                .delay(500)
                .style('opacity', 1);



        // Append tooltip
        // ------------------------------

        // Add tooltip on circle hover
        circles
                .on("mouseover", function (d) {
                    tooltip.offset([-15, 0]).show(d);

                    // Animate circle radius
                    d3.select(this).transition().duration(250).attr('r', 4);
                })
                .on("mouseout", function (d) {
                    tooltip.hide(d);

                    // Animate circle radius
                    d3.select(this).transition().duration(250).attr('r', 3);
                });

        // Change tooltip direction of first point
        // to always keep it inside chart, useful on mobiles
        lines.each(function (d) {
            d3.select(d3.select(this).selectAll('circle')[0][0])
                    .on("mouseover", function (d) {
                        tooltip.offset([0, 15]).direction('e').show(d);

                        // Animate circle radius
                        d3.select(this).transition().duration(250).attr('r', 4);
                    })
                    .on("mouseout", function (d) {
                        tooltip.direction('n').hide(d);

                        // Animate circle radius
                        d3.select(this).transition().duration(250).attr('r', 3);
                    });
        })

        // Change tooltip direction of last point
        // to always keep it inside chart, useful on mobiles
        lines.each(function (d) {
            d3.select(d3.select(this).selectAll('circle')[0][d3.select(this).selectAll('circle').size() - 1])
                    .on("mouseover", function (d) {
                        tooltip.offset([0, -15]).direction('w').show(d);

                        // Animate circle radius
                        d3.select(this).transition().duration(250).attr('r', 4);
                    })
                    .on("mouseout", function (d) {
                        tooltip.direction('n').hide(d);

                        // Animate circle radius
                        d3.select(this).transition().duration(250).attr('r', 3);
                    })
        })



        // Update chart on date change
        // ------------------------------

        // Set variable for updating visualization
        var lineUpdate = d3.transition(lines);

        // Update lines
        lineUpdate.select("path")
                .attr("d", function (d, i) {
                    return line(d.values);
                });

        // Update circles
        lineUpdate.selectAll("circle")
                .attr("cy", function (d, i) {
                    return y(d.value)
                })
                .attr("cx", function (d, i) {
                    return x(d.date)
                });

        // Update vertical axes
        d3.transition(svg)
                .select(".d3-axis-vertical")
                .call(yAxis);

        // Update horizontal axes
        d3.transition(svg)
                .select(".d3-axis-horizontal")
                .attr("transform", "translate(0," + height + ")")
                .call(xAxis);



        // Resize chart
        // ------------------------------

        // Call function on window resize
        $(window).on('resize', customerSatisfactionResize);

        // Call function on sidebar width change
        $(document).on('click', '.sidebar-control', customerSatisfactionResize);

        // Resize function
        // 
        // Since D3 doesn't support SVG resize by default,
        // we need to manually specify parts of the graph that need to 
        // be updated on window resize
        function customerSatisfactionResize() {

            // Layout
            // -------------------------

            // Define width
            width = d3Container.node().getBoundingClientRect().width - margin.left - margin.right;

            // Main svg width
            container.attr("width", width + margin.left + margin.right);

            // Width of appended group
            svg.attr("width", width + margin.left + margin.right);

            // Horizontal range
            x.range([0, width]);

            // Vertical range
            y.range([height, 0]);


            // Chart elements
            // -------------------------

            // Horizontal axis
            svg.select('.d3-axis-horizontal').call(xAxis);

            // Vertical axis
            svg.select('.d3-axis-vertical').call(yAxis.tickSize(0 - width));

            // Lines
            svg.selectAll('.d3-line').attr("d", function (d, i) {
                return line(d.values);
            });

            // Circles
            svg.selectAll('.d3-line-circle').attr("cx", function (d, i) {
                return x(d.date)
            })
        }
    }

    var seed = 1;
    function random(from, to) {
        var x = Math.sin(seed++) * 10000;
        var s = x - Math.floor(x);

        return Math.floor(s * (to - from) + from);
    }

    csl.formatted = [];

    var dates = [];
    for (var k = 26; k > 10; k--) {
        dates.push("2017/5/" + k + "");
    }

    var p1 = random(40, 90);
    var p2 = random(40, 90);
    var p3 = random(40, 90);

    for (var k in dates) {
        p1 += random(-3, 3);
        p2 += random(-3, 3);
        p3 += random(-3, 3);
        csl.formatted.push({
            type: "val3",
            date: dates[k],
            "18-25": p1,
            "26-41": p2,
            "42-64": p3
        });
    }

//    csl.redraw();
    d3.csv("assets/demo_data/dashboard/app_sales.csv", function (error, data) {
//        csl.formatted = data;
//        console.log(csl.formatted)
        csl.redraw();
    });
}


function updateNotifications() {
    
    $.ajax({url: "GetNotificationServlet", success: function(result){
        var $dom = $("#notificationList");
        
        var notifs = JSON.parse(result);
        
        var res = "";
        
        var counter = 0;
        for( var k in notifs ) {
            var n = notifs[k];
            res += "" +
"                    <li class=\"media\">\n" +
"                        <div class=\"media-left\">\n" +
"                            <a href=\"#\" class=\"btn border-teal-400 text-teal btn-flat btn-rounded btn-icon btn-xs\"><i class=\"" + n.icon + "\"></i></a>\n" +
"                        </div>\n" +
"                        <div class=\"media-body\">" + n.content +
"                            <div class=\"media-annotation\">" + n.time + "</div>\n" +
"                        </div>\n" +
"                        <div class=\"media-right media-middle\">\n" +
"                            <ul class=\"icons-list\">\n" +
"                                <li>\n" +
"                                    <a href=\"#\"><i class=\"icon-arrow-right13\"></i></a>\n" +
"                                </li>\n" +
"                            </ul>\n" +
"                        </div>\n" +
"                    </li>";

            if( counter++ > 8 ) {
                break;
            }
        }

        $dom.html(res);
        $("#div1").html(result);
    }});
}

function updateIncome() {
    
    $.ajax({url: "GetIncomeServlet", success: function(result){
        var incomes = JSON.parse(result);
        
        for( var k in incomes ) {
            var n = incomes[k];
            
        }
    }});
}
