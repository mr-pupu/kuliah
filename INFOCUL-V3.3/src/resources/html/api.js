(function() {
	var infowindow;

	window.onload = function() {

		var options = {
			zoom : 17,
			center : new google.maps.LatLng(-7.290404, 112.779973),
			mapTypeId : google.maps.MapTypeId.ROADMAP
		};

		var map = new google.maps.Map(document.getElementById('map'), options);
		var bounds = new google.maps.LatLngBounds();
		var places = [];
		places.push(new google.maps.LatLng(-7.308856, 112.673749));
		places.push(new google.maps.LatLng(-7.258119, 112.747822));
		places.push(new google.maps.LatLng(-7.341100, 112.703351));
		places.push(new google.maps.LatLng(-7.172605, 112.653044));
		places.push(new google.maps.LatLng(-7.657064, 112.724519));
		places.push(new google.maps.LatLng(-7.36053, 112.686764));
		var infowindow;

		for ( var i = 0; i < places.length; i++) {
		
			var marker = new google.maps.Marker({
				position : places[i],
				map : map,
				title : 'Warung ke ' + i,
				animation: google.maps.Animation.DROP
			});
		
			(function(i, marker) {
				google.maps.event
						.addListener(
								marker,
								'click',
								function() {
									if (!infowindow) {
										infowindow = new google.maps.InfoWindow();
									}
									if (i == 0) {
										var content = '<div id="info">'
												+ '<img src="../images/baksopak heri brengos_map.jpg" alt="" />'
												+ '<h2>Bakso pak heri Brengos</h2>'
												+ '<p>Jl. Lidah Wetan No.14 Raya Menganti</p>'
												+ '</div>';
										infowindow.setContent(content);
									} else if (i == 1) {
										var content = '<div id="info">'
												+ '<img src="../images/sotoototjimerto_map.jpg" alt="" />'
												+ '<h2>Soto Otot Jimerto</h2>'
												+ '<p>Jl. Jimerto 02, Surabaya</p>'
												+ '</div>';
										infowindow.setContent(content);
									} else if (i == 2) {
										var content = '<div id="info">'
												+ '<img src="../images/kikilsapipakmadekan_map.jpg" alt="" />'
												+ '<h2>Kikil Sapi Pak Madekan</h2>'
												+ '<p>Jl. Sepanjang Tani 54, Sepanjang</p>'
												+ '</div>';
										infowindow.setContent(content);
									} else if (i == 3) {
										var content = '<div id="info">'
												+ '<img src="../images/bandengpak rasid_map.jpg" alt="" />'
												+ '<h2>Depot Bandeng Pak Rasid</h2>'
												+ '<p>Jl. Veteran gang XIII no. 5, Gresik</p>'
												+ '</div>';
										infowindow.setContent(content);
									} else if (i == 4) {
										var content = '<div id="info">'
												+ '<img src="../images/warungpaksholeh_map.jpg" alt="" />'
												+ '<h2>Warung Lesehan Pak Sholeh</h2>'
												+ '<p>Desa Tunggulwulung<br>Pandaan, Pasuruan</p>'
												+ '</div>';

										infowindow.setContent(content);
									} else if (i == 5) {
										var content = '<div id="info">'
												+ '<img src="../images/satebarongan_map.jpg" alt="" />'
												+ '<h2>Sate Barongan</h2>'
												+ '<p>Jl. Wonocolo 13 Sepanjang, Sidoarjo</p>'
												+ '</div>';
										infowindow.setContent(content);
									}
									infowindow.open(map, marker);
								});
			})(i, marker);
			bounds.extend(places[i]);
		}
		map.fitBounds(bounds)
	};
})();