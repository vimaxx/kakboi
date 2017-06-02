/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// Configuration

function initMap(data) {
    console.log(data);
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
        hoverColor: true,
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
        markers: data,
        onMarkerClick: function (event, index) {
            console.log(index);
            changeBranch(index);
        }
    });
}

function changeBranch(index) {
    $("#branchDetails > div").addClass("hidden");
    $("#branchDetail" + index).removeClass("hidden");
    $("#branchContainer > div").addClass("hidden");
    $("#branchRow" + index).removeClass("hidden");
}

    