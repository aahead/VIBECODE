let lastHole;
let timeUp = false;
let score = 0;
let difficulty = 'medium';  // Default difficulty
let gameMode = '15-seconds';  // Start with 15-second mode
const scoreDisplay = document.getElementById('score');
const startBtn = document.getElementById('start-btn');
const difficultyBtns = document.querySelectorAll('.difficulty-btn');
const holes = document.querySelectorAll('.hole');
const gameOverDisplay = document.getElementById('game-over');
const finalScoreDisplay = document.getElementById('final-score');
const playAgainBtn = document.getElementById('play-again-btn');
const modeToggleBtn = document.getElementById('mode-toggle-btn'); // Mode toggle button
const stopBtn = document.getElementById('stop-btn');  // Stop button for endless mode

// Adjust timing based on difficulty
function randomTime(min, max) {
  switch (difficulty) {
    case 'easy':
      return Math.round(Math.random() * (max - min) + min + 1000); // Slow moles
    case 'medium':
      return Math.round(Math.random() * (max - min) + min); // Normal moles
    case 'hard':
      return Math.round(Math.random() * (max - min) + min - 500); // Fast moles
    default:
      return Math.round(Math.random() * (max - min) + min);
  }
}

function randomHole(holes) {
  const idx = Math.floor(Math.random() * holes.length);
  const hole = holes[idx];
  if (hole === lastHole) {
    return randomHole(holes);
  }
  lastHole = hole;
  return hole;
}

function showMole() {
  const time = randomTime(500, 1000); // Modify the appearance rate based on difficulty
  const hole = randomHole(holes);

  hole.classList.add('mole');
  setTimeout(() => {
    hole.classList.add('show');
  }, 0);

  setTimeout(() => {
    hole.classList.remove('show');
    hole.classList.remove('mole');
    if (!timeUp) showMole();  // Continue spawning moles while the game is active
  }, time + 1000);
}

function startGame() {
  score = 0;
  scoreDisplay.textContent = score;
  timeUp = false;
  gameOverDisplay.style.display = 'none';  // Hide game over screen
  holes.forEach(hole => hole.classList.remove('show', 'mole', 'missed', 'hit'));  // Clear previous moles and effects
  showMole();  // Start showing moles

  // If 15-second mode, stop after 15 seconds
  if (gameMode === '15-seconds') {
    setTimeout(() => {
      timeUp = true;  // Stop the game after 15 seconds
      endGame();
    }, 15000);  // 15 seconds game duration
  }
}

function endGame() {
  finalScoreDisplay.textContent = score;
  gameOverDisplay.style.display = 'block';  // Show game over screen
}

function setDifficulty(level) {
  difficulty = level;
  resetGame();  // Reset the game to apply the new difficulty
}

function resetGame() {
  score = 0;
  scoreDisplay.textContent = score;
  timeUp = false;
  holes.forEach(hole => hole.classList.remove('show', 'mole', 'missed', 'hit'));  // Clear previous moles
}

function toggleMode() {
  if (gameMode === '15-seconds') {
    gameMode = 'endless';
    modeToggleBtn.textContent = 'Switch to 15-Second Mode';
    stopBtn.style.display = 'inline-block';  // Show the stop button
  } else {
    gameMode = '15-seconds';
    modeToggleBtn.textContent = 'Switch to Endless Mode';
    stopBtn.style.display = 'none';  // Hide the stop button
  }

  resetGame();  // Reset the game to apply the new mode
}

startBtn.addEventListener('click', startGame);

difficultyBtns.forEach(btn => {
  btn.addEventListener('click', (e) => {
    const level = e.target.dataset.difficulty;
    setDifficulty(level);  // Set the difficulty based on the button clicked
    resetGame();  // Reset the game state for the new difficulty level
    
    // Remove the active class from all buttons
    difficultyBtns.forEach(button => button.classList.remove('active'));
    
    // Add the active class to the clicked button
    e.target.classList.add('active');
  });
});

modeToggleBtn.addEventListener('click', toggleMode);  // Switch between 15-second and endless mode

stopBtn.addEventListener('click', () => {
  timeUp = true;  // Stop the game when the stop button is pressed
  endGame();
});

holes.forEach(hole => {
  hole.addEventListener('click', () => {
    if (hole.classList.contains('mole')) {
      hole.classList.add('hit');
      score++;
      scoreDisplay.textContent = score;
      setTimeout(() => {
        hole.classList.remove('mole');
        hole.classList.remove('hit');
      }, 300);
    } else {
      hole.classList.add('missed');
      setTimeout(() => {
        hole.classList.remove('missed');
      }, 500);
    }
  });
});

playAgainBtn.addEventListener('click', () => {
  gameOverDisplay.style.display = 'none';  // Hide the game over screen
  resetGame();  // Restart the game with the selected difficulty and mode
});
