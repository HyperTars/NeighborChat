var map;
var marker;

function initMap() {
    myLatLng = {lat: 40.693809, lng: -73.986622}
    map = new google.maps.Map(document.getElementById('map'), {
        center: myLatLng,
        zoom: 14
    });

    marker = new google.maps.Marker({
　　　　　position: myLatLng,
　　　　　map: map,
　　　　　title: 'Hello World!'
　　});

    google.maps.event.addListener(map,'click',function(event) {
        if (marker != null) marker.setMap(null);
        
        currentLatLng = {lat: event.latLng.lat(), lng: event.latLng.lng()};
        marker = new google.maps.Marker({
    　　    position: currentLatLng,
    　　    map: map,
    　　    title: 'Hello World!'
    　　});

        marker.setMap(map);

        console.info(event.latLng.lat());  // print lat of click pos
        console.info(event.latLng.lng());  // print lng of click pos
    });

}