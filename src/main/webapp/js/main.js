$('#slider .ui-item').on('click', function(){
  var indexItem = $(this).index();

  $('#slider .ui-item').removeClass('is-active');
  $(this).addClass('is-active');
  $('.index-item').hide();
  $('.index-item').eq(indexItem).show();
});