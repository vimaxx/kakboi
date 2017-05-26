/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// Configuration
$('#branchMap').vectorMap({
    map: 'au_mill',
    backgroundColor: 'transparent',
    scaleColors: ['#C8EEFF', '#0071A4'],
    normalizeFunction: 'polynomial',
    regionStyle: {
        initial: {
            fill: '#D6E1ED'
        }
    },
    hoverOpacity: 0.7,
    hoverColor: false,
    markerStyle: {
        initial: {
            r: 7,
            'fill': '#336BB5',
            'fill-opacity': 0.8,
            'stroke': '#fff',
            'stroke-width': 1.5,
            'stroke-opacity': 0.9
        },
        hover: {
            'stroke': '#fff',
            'fill-opacity': 1,
            'stroke-width': 1.5
        }
    },
    focusOn: {
        x: 0.5,
        y: -0.5,
        scale: 1.3
    },
    zoomMin: 1.3,
    markers: [
        {latLng: [-33, 151], name: 'Sydney'},
        {latLng: [43.73, 7.41], name: 'Monaco'},
        {latLng: [40.726, -111.778], name: 'Salt Lake City'},
        {latLng: [39.092, -94.575], name: 'Kansas City'},
        {latLng: [25.782, -80.231], name: 'Miami'},
        {latLng: [8.967, -79.458], name: 'Panama City'},
        {latLng: [19.400, -99.124], name: 'Mexico City'}
    ],
    
    onMarkerLabelShow: function(event, label, index) {
        label.html(
            ''+label.html()+'<br>'+
            'Population: '+index+'<br>'+
            'Unemployment rate: v%'
        );
    },

    onRegionLabelShow: function(event, label, code) {
        label.html(
            ''+label.html()+'<br>'+
            'Population : c%'
        );
    },
    onMarkerClick: function (event, label, index) {
        
    },
    onRegionClick: function (event, label, code) {
        
    }
});
    
    