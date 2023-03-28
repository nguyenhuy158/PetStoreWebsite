const slider = document.querySelector('.slider');
const prevBtn = slider.querySelector('.prev');
const nextBtn = slider.querySelector('.next');
const images = slider.querySelectorAll('img');
let currentSlide = 0;

function showSlide(n) {
  images.forEach(img => {
    img.style.display = 'none';
  });
  images[n].style.display = 'block';
}

function nextSlide() {
  currentSlide++;
  if (currentSlide >= images.length) {
    currentSlide = 0;
  }
  showSlide(currentSlide);
}

function prevSlide() {
  currentSlide--;
  if (currentSlide < 0) {
    currentSlide = images.length - 1;
  }
  showSlide(currentSlide);
}

showSlide(currentSlide);
nextBtn.addEventListener('click', nextSlide);
prevBtn.addEventListener('click', prevSlide);