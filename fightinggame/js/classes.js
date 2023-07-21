class Sprite {
	constructor({ 
		position, 
		imageSrc, 
		scale = 1, 
		framesMax = 1, 
		offset = {x:0, y:0} 
	}) {
		this.position = position
		this.image = new Image()
		this.image.src = imageSrc
		this.width = 50
		this.height = 150
		this.scale = scale
		this.framesMax = framesMax
		this.framesCurrent = 0
		this.framesElapsed = 0
		this.framesHold = 5
		this.offset = offset

		// checks for size of image given
		this.image.onload = () => {
      	this.width = this.image.width / this.framesMax;
      	this.height = this.image.height;
    	};
	}

	draw() {
		c.drawImage(
			this.image,
			// find frames between each fire and sets it initally to fire 1
			this.framesCurrent * (this.image.width / this.framesMax), 
			0,
			// divides the total sprite sheet by number of fires on sheet
			this.image.width / this.framesMax, 
			this.image.height,
			this.position.x - this.offset.x * this.scale, 
			this.position.y - this.offset.y * this.scale, 
			(this.image.width / this.framesMax) * this.scale, 
			this.image.height * this.scale
			)
	}

	animateFrames() {

		this.framesElapsed++
		if (this.framesElapsed % this.framesHold === 0) {
		// makes sure that framesMax is 0 for the background
		// and to increase framesCurrent by 1 to get the next image 
		// until it reaches 8 then it restarts
			if (this.framesCurrent < this.framesMax - 1) {
				this.framesCurrent++
			} else {
				this.framesCurrent = 0
			}
		}	
	}

	update() {
		this.draw()
		this.animateFrames()	
	}
}

class Fighter extends Sprite {
	constructor({ 
		position, 
		velocity, 
		color = 'red', 
		imageSrc, 
		scale = 1, 
		framesMax = 1,
		offset = {x:0, y:0},
		sprites
	}) {
		super({
			position,
			imageSrc,
			scale,
			framesMax,
			offset
		})

		this.velocity = velocity
		this.width = 50
		this.height = 150
		this.lastKey
		this.attackBox = {
			position: {
				x: this.position.x,
				y: this.position.y
			},
			offset,
			width: 100,
			height: 50
		}
		this.color = color
		this.isAttacking
		this.health = 100
		this.framesCurrent = 0
		this.framesElapsed = 0
		this.framesHold = 5
		this.sprites = sprites

		for (const sprite in this.sprites) {
			sprites[sprite].image = new Image()
			sprites[sprite].image.src = sprites[sprite].imageSrc
		}
	}

	update() {
  this.draw();
  this.animateFrames();

  this.attackBox.position.x = this.position.x + this.attackBox.offset.x;
  this.attackBox.position.y = this.position.y;

  this.position.x += this.velocity.x;
  this.position.y += this.velocity.y;

  if (this.position.y + this.height + this.velocity.y >= canvas.height) {
    this.velocity.y = 0;
  } else {
    this.velocity.y += gravity;
  }

}

	attack() {
		this.isAttacking = true
		setTimeout(() => {
			this.isAttacking = false
		}, 100)
	}

	switchSprite(sprite) {
		switch (sprite) {
		case 'idle':
			if (this.image !== this.sprites.idle.image) {
			this.image = this.sprites.idle.image
		this.framesMax = this.sprites.idle.framesMax
		this.scale = this.sprites.idle.scale
		this.offset = this.sprites.idle.offset
		//this.framesCurrent = 0
	}
			break
		case 'walk':
			if (this.image !== this.sprites.walk.image) {
			this.image = this.sprites.walk.image
		this.framesMax = this.sprites.walk.framesMax
		this.scale = this.sprites.walk.scale
		this.offset = this.sprites.walk.offset
		//this.framesCurrent = 0
	}
			break
		case 'jump':
			if (this.image !== this.sprites.jump.image) {
			this.image = this.sprites.jump.image
		this.framesMax = this.sprites.jump.framesMax
		this.scale = this.sprites.jump.scale
		this.offset = this.sprites.jump.offset
		//this.framesCurrent = 0
	}
			break
		}
	}
}
