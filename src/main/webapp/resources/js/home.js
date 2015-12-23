$(function() {

	var idxCombinations = 0;
	var combinations = [];
	var votes = "";

	$.ajax({
		url : 'restaurants',
		success : function(data) {
			console.log('Loaded restaurants...');
			combinations = data;
			showRestaurant(combinations[idxCombinations].restaurantLeft,
					combinations[idxCombinations].restaurantRight);
		},
		error : function() {
			console.log('Ocorreu um erro ao carregar os restaurantes.');
		}
	});

	function showRestaurant(restaurantLeft, restaurantRight) {
		$('#imgLeft').attr('src', restaurantLeft.imagePath);
		$('#nameLeft').html(restaurantLeft.name);
		$('#restaurantLeft').attr('data-id', restaurantLeft.id);

		$('#imgRight').attr('src', restaurantRight.imagePath);
		$('#nameRight').html(restaurantRight.name);
		$('#restaurantRight').attr('data-id', restaurantRight.id);

		idxCombinations++;
	}

	$('#restaurantLeft').click(function(event) {
		console.log('Select Restaurant Left');
		registerVote();

	});

	$('#restaurantRight').click(function(event) {
		console.log('Select Restaurant Right');
		registerVote();

	});

	$('#formUser')
			.submit(
					function(event) {
						console.log('ooopsss... consle...');
						if ($('#name').val().trim().length == 0
								|| $('#email').val().trim().length == 0) {
							event.preventDefault();
							$('#erroAlert')
									.html(
											'Os campos <b>Nome</b> e <b>E-mail</b> são de preenchimento obrigat\u00f3rio. * Não divulgaremos essas informações')
									.show();
							console.log('Alerta erro');
						}

						if (!validateEmail($('#email').val().trim())) {
							event.preventDefault();
							$('#erroAlert').html('E-mail inv\u00e1lido').show();
							console.log('Alerta erro email');
						}
					});

	function validateEmail($email) {
		var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
		return emailReg.test($email);
	}

	var msgCustom = [ 'Essa escolha \u00e9 dif\u00edcil!',
			'Essa escolha \u00e9 f\u00e1cil!', 'Estou em d\u00favida!',
			'J\u00e1 sei qual voc\u00ea ir\u00e1 escolher!',
			'Huuuuummmm, deu fome s\u00f3 de pensar nesses restaurantes!',
			'\u0022 Com grandes escolhas vem grandes responsabilidades! \u0022',
			'Sabia que essa voc\u00ea ficaria com d\u00favida!',
			'Bem que poderia escolher as duas, hahahaha!' ];

	function registerVote() {
		if (idxCombinations < combinations.length) {
			var id = $(event.currentTarget).attr('data-id');
			votes += id + ",";

			$('#message').html(
					msgCustom[randomIntFromInterval(0, msgCustom.length - 1)]);

			var porcentagem = idxCombinations / combinations.length * 100 + '%';
			$('#progress-bar').width(porcentagem).html(porcentagem);

			showRestaurant(combinations[idxCombinations].restaurantLeft,
					combinations[idxCombinations].restaurantRight);
		} else {
			$('#restaurantLeft').remove();
			$('#restaurantRight').remove();
			$('#progress').hide();

			$('#votes').val(votes);

			$('#message')
					.html(
							'Informe seu Nome, E-mail e clique em cadastrar, encaminharemos para o resultado.');
			$('#register').show();
		}
	}

	function randomIntFromInterval(min, max) {
		return Math.floor(Math.random() * (max - min + 1) + min);
	}
});