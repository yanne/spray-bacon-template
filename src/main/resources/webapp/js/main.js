var stream = $('.action').asEventStream("click")

stream.onValue((function(e) {
    $(e.target).css("visibility", "hidden")
  Bacon.fromPromise($.ajax({url: 'getGreeting'})).onValue(function (value) {
    $('.content').text(value)
  })
}))
