document.addEventListener("DOMContentLoaded", loaded);

function loaded() {
  // 캔버스 가져오기
  var canvas = document.getElementById("canvas");
  var ctx = canvas.getContext('2d');

  // 게임 요소 초기화
  var ball = {
    x: canvas.width / 2,
    y: canvas.height / 2,
    radius: 10,
    speed: 5,
    velocityX: 5,
    velocityY: 5,
    color: "WHITE"
  };

  var user1 = {
    x: 0,
    y: (canvas.height - 100) / 2,
    width: 10,
    height: 100,
    score: 0,
    color: "WHITE"
  };

  var user2 = {
    x: canvas.width - 10,
    y: (canvas.height - 100) / 2,
    width: 10,
    height: 100,
    score: 0,
    color: "WHITE"
  };

  function drawNet() {
    ctx.fillStyle = "#fff";
    for (let i = 0; i < canvas.height; i += 40) {
      ctx.fillRect(canvas.width / 2 - 1, i, 2, 20);
    }
  }

  function drawRect(x, y, w, h, color) {
    ctx.fillStyle = color;
    ctx.fillRect(x, y, w, h);
  }

  function drawBall(x, y, r, color) {
    ctx.fillStyle = color;
    ctx.beginPath();
    ctx.arc(x, y, r, 0, Math.PI * 2, true);
    ctx.closePath();
    ctx.fill();
  }

  function drawText(text, x, y, color) {
    ctx.fillStyle = color;
    ctx.font = "75px fantasy";
    ctx.fillText(text, x, y);
  }

  function draw() {
    // 캔버스 초기화
    drawRect(0, 0, canvas.width, canvas.height, "BLACK");

    // 중앙 선 그리기
    drawNet();

    // 패들 그리기
    drawRect(user1.x, user1.y, user1.width, user1.height, user1.color);
    drawRect(user2.x, user2.y, user2.width, user2.height, user2.color);

    // 공 그리기
    drawBall(ball.x, ball.y, ball.radius, ball.color);

    // 점수 그리기
    drawText(user1.score, canvas.width / 4, canvas.height / 5, "WHITE");
    drawText(user2.score, (3 * canvas.width) / 4, canvas.height / 5, "WHITE");
  }

  function update() {
	    // 공 위치 업데이트
	    ball.x += ball.velocityX;
	    ball.y += ball.velocityY;
	
		// 왼쪽 벽 충돌 검사
		if(ball.x - ballSize < 0) {
	  		ballSpeedX = -ballSpeedX;
	  		ball.x = ballSize;
		}
	
		// 오른쪽 벽 충돌 검사
		if(ball.x + ballSize > canvas.width) {
	 		 ballSpeedX = -ballSpeedX;
	 		 ball.x = canvas.width - ballSize;
	 	}
 	}
 }