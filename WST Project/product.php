<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>MangaVault – Demon Slayer</title>
  <link href="https://fonts.googleapis.com/css2?family=Bebas+Neue&family=Barlow:wght@400;500;600;700&family=Barlow+Condensed:wght@700;800&display=swap" rel="stylesheet"/>
  <style>
    *, *::before, *::after { box-sizing: border-box; margin: 0; padding: 0; }

    :root {
      --bg:        #111111;
      --surface:   #1a1a1a;
      --surface2:  #222222;
      --border:    #2e2e2e;
      --red:       #e02020;
      --red-dark:  #b81818;
      --text:      #f0f0f0;
      --muted:     #888888;
      --strike:    #555555;
      --gold:      #f5a623;
    }

    body {
      background: var(--bg);
      color: var(--text);
      font-family: 'Barlow', sans-serif;
      min-height: 100vh;
    }

    /* ── NAV ── */
    nav {
      background: #0d0d0d;
      border-bottom: 1px solid var(--border);
      display: flex;
      justify-content: center;
      gap: 0;
    }
    nav a {
      text-decoration: none;
      color: var(--muted);
      font-family: 'Barlow Condensed', sans-serif;
      font-weight: 700;
      font-size: 0.85rem;
      letter-spacing: 0.12em;
      padding: 1.1rem 2.4rem;
      text-transform: uppercase;
      position: relative;
      transition: color 0.2s;
    }
    nav a:hover { color: var(--text); }
    nav a.active {
      color: var(--text);
    }
    nav a.active::after {
      content: '';
      position: absolute;
      bottom: 0; left: 50%;
      transform: translateX(-50%);
      width: 60%;
      height: 2px;
      background: var(--red);
      border-radius: 2px;
    }

    /* ── BREADCRUMB ── */
    .breadcrumb {
      padding: 1rem 3rem;
      font-size: 0.75rem;
      color: var(--muted);
      letter-spacing: 0.04em;
    }
    .breadcrumb span { color: var(--text); }

    /* ── PRODUCT LAYOUT ── */
    .product-page {
      max-width: 1100px;
      margin: 1.5rem auto 4rem;
      padding: 0 3rem;
      display: grid;
      grid-template-columns: 1fr 1fr;
      gap: 3.5rem;
      align-items: start;
    }

    /* ── GALLERY ── */
    .gallery { display: flex; flex-direction: column; gap: 1rem; }

    .main-image {
      width: 100%;
      aspect-ratio: 3/4;
      background: var(--surface2);
      border-radius: 6px;
      border: 1px solid var(--border);
      overflow: hidden;
      position: relative;
      cursor: zoom-in;
    }
    .main-image img {
      width: 100%; height: 100%;
      object-fit: cover;
      transition: transform 0.4s ease;
    }
    .main-image:hover img { transform: scale(1.04); }

    /* placeholder shimmer when no image */
    .main-image.empty {
      background: linear-gradient(135deg, #1e1e1e 0%, #2a2a2a 50%, #1e1e1e 100%);
      background-size: 200% 200%;
      animation: shimmer 2s infinite;
    }
    @keyframes shimmer {
      0%   { background-position: 0% 50%; }
      50%  { background-position: 100% 50%; }
      100% { background-position: 0% 50%; }
    }
    .main-image.empty::after {
      content: '📖';
      position: absolute; inset: 0;
      display: flex; align-items: center; justify-content: center;
      font-size: 4rem; opacity: 0.12;
      display: grid; place-items: center;
    }

    .thumbnails {
      display: flex;
      gap: 0.6rem;
    }
    .thumb {
      width: 70px; height: 70px;
      border-radius: 5px;
      background: var(--surface2);
      border: 1px solid var(--border);
      cursor: pointer;
      overflow: hidden;
      transition: border-color 0.2s, transform 0.15s;
      flex-shrink: 0;
    }
    .thumb img { width: 100%; height: 100%; object-fit: cover; }
    .thumb.empty {
      background: linear-gradient(135deg, #1e1e1e, #2a2a2a);
      animation: shimmer 2.5s infinite;
    }
    .thumb:hover, .thumb.active {
      border-color: var(--red);
      transform: translateY(-2px);
    }

    /* ── PRODUCT INFO ── */
    .product-info { display: flex; flex-direction: column; gap: 1.2rem; }

    .title-block {
      border-left: 3px solid var(--red);
      padding-left: 0.9rem;
    }
    .title-block h1 {
      font-family: 'Bebas Neue', sans-serif;
      font-size: 3rem;
      letter-spacing: 0.06em;
      line-height: 0.95;
      color: #fff;
    }
    .subtitle {
      font-size: 0.85rem;
      color: var(--muted);
      margin-top: 0.35rem;
      letter-spacing: 0.02em;
    }

    /* stars */
    .rating {
      display: flex;
      align-items: center;
      gap: 0.5rem;
    }
    .stars { display: flex; gap: 2px; }
    .star { color: var(--gold); font-size: 1.1rem; }
    .star.half { position: relative; color: var(--border); }
    .star.half::before {
      content: '★';
      color: var(--gold);
      position: absolute; left: 0;
      clip-path: inset(0 50% 0 0);
    }
    .review-count {
      font-size: 0.8rem;
      color: var(--muted);
    }

    /* price */
    .price-block {
      display: flex;
      align-items: baseline;
      gap: 0.75rem;
    }
    .price {
      font-family: 'Barlow Condensed', sans-serif;
      font-size: 2.4rem;
      font-weight: 800;
      color: #fff;
    }
    .price-original {
      font-size: 1rem;
      color: var(--strike);
      text-decoration: line-through;
    }
    .discount-badge {
      background: var(--red);
      color: #fff;
      font-size: 0.7rem;
      font-weight: 700;
      padding: 0.2rem 0.5rem;
      border-radius: 3px;
      letter-spacing: 0.05em;
    }

    .divider {
      border: none;
      border-top: 1px solid var(--border);
      margin: 0.2rem 0;
    }

    /* volume selector */
    .volume-label {
      font-size: 0.75rem;
      text-transform: uppercase;
      letter-spacing: 0.1em;
      color: var(--muted);
      margin-bottom: 0.5rem;
    }
    .volumes {
      display: flex;
      gap: 0.6rem;
      flex-wrap: wrap;
    }
    .vol-btn {
      padding: 0.5rem 1.1rem;
      font-family: 'Barlow Condensed', sans-serif;
      font-size: 0.8rem;
      font-weight: 700;
      letter-spacing: 0.1em;
      border-radius: 4px;
      border: 1px solid var(--border);
      background: transparent;
      color: var(--muted);
      cursor: pointer;
      text-transform: uppercase;
      transition: all 0.15s;
    }
    .vol-btn:hover {
      border-color: var(--red);
      color: var(--text);
    }
    .vol-btn.active {
      background: var(--red);
      border-color: var(--red);
      color: #fff;
    }
    .vol-btn:disabled {
      opacity: 0.35;
      cursor: not-allowed;
    }

    /* quantity */
    .qty-label {
      font-size: 0.75rem;
      text-transform: uppercase;
      letter-spacing: 0.1em;
      color: var(--muted);
      margin-bottom: 0.5rem;
    }
    .qty-control {
      display: flex;
      align-items: center;
      gap: 0;
      width: fit-content;
      border: 1px solid var(--border);
      border-radius: 4px;
      overflow: hidden;
    }
    .qty-btn {
      width: 36px; height: 36px;
      background: var(--surface2);
      border: none;
      color: var(--text);
      font-size: 1.1rem;
      cursor: pointer;
      transition: background 0.15s;
    }
    .qty-btn:hover { background: var(--border); }
    .qty-value {
      width: 48px;
      text-align: center;
      background: transparent;
      border: none;
      border-left: 1px solid var(--border);
      border-right: 1px solid var(--border);
      color: var(--text);
      font-family: 'Barlow Condensed', sans-serif;
      font-size: 1rem;
      font-weight: 700;
      padding: 0 0.5rem;
      height: 36px;
    }
    .qty-value:focus { outline: none; }

    /* action buttons */
    .actions {
      display: grid;
      grid-template-columns: 1fr 1.4fr;
      gap: 0.75rem;
      margin-top: 0.5rem;
    }
    .btn {
      padding: 0.9rem 1.2rem;
      font-family: 'Barlow Condensed', sans-serif;
      font-size: 0.85rem;
      font-weight: 700;
      letter-spacing: 0.12em;
      text-transform: uppercase;
      border-radius: 4px;
      border: none;
      cursor: pointer;
      transition: all 0.2s;
    }
    .btn-cart {
      background: transparent;
      border: 1px solid var(--border);
      color: var(--text);
    }
    .btn-cart:hover {
      border-color: var(--text);
      background: rgba(255,255,255,0.04);
    }
    .btn-buy {
      background: var(--red);
      color: #fff;
    }
    .btn-buy:hover {
      background: var(--red-dark);
      transform: translateY(-1px);
      box-shadow: 0 6px 20px rgba(224,32,32,0.35);
    }
    .btn-buy:active { transform: translateY(0); box-shadow: none; }

    /* toast */
    #toast {
      position: fixed;
      bottom: 2rem; right: 2rem;
      background: #222;
      border: 1px solid var(--border);
      border-left: 3px solid var(--red);
      color: var(--text);
      font-size: 0.82rem;
      padding: 0.75rem 1.2rem;
      border-radius: 5px;
      opacity: 0;
      transform: translateY(12px);
      transition: all 0.3s;
      pointer-events: none;
      z-index: 999;
    }
    #toast.show { opacity: 1; transform: translateY(0); }

    /* ── RESPONSIVE ── */
    @media (max-width: 720px) {
      .product-page {
        grid-template-columns: 1fr;
        padding: 0 1.2rem;
        gap: 2rem;
      }
      .breadcrumb { padding: 0.75rem 1.2rem; }
      .title-block h1 { font-size: 2.4rem; }
    }
  </style>
</head>
<body>

<!-- NAV -->
<nav>
  <a href="#">Home</a>
  <a href="#" class="active">Products</a>
  <a href="#">New Arrivals</a>
  <a href="#">About Us</a>
</nav>

<!-- BREADCRUMB -->
<div class="breadcrumb">
  Products &rsaquo; Manga &rsaquo; <span>Demon Slayer</span>
</div>

<!-- PRODUCT PAGE -->
<div class="product-page">

  <!-- GALLERY -->
  <div class="gallery">
    <div class="main-image empty" id="mainImage">
      <!-- swap out the empty class and add <img> when you have a cover image -->
      <!-- example: <img src="covers/ds-vol1.jpg" alt="Demon Slayer Vol.1"> -->
    </div>
    <div class="thumbnails" id="thumbRow">
      <div class="thumb empty active"></div>
      <div class="thumb empty"></div>
      <div class="thumb empty"></div>
      <div class="thumb empty"></div>
      <div class="thumb empty"></div>
    </div>
  </div>

  <!-- INFO -->
  <div class="product-info">

    <div class="title-block">
      <h1>Demon Slayer</h1>
      <div class="subtitle" id="subtitleText">Infinite Castle Arc, Volume 1</div>
    </div>

    <div class="rating">
      <div class="stars">
        <span class="star">★</span>
        <span class="star">★</span>
        <span class="star">★</span>
        <span class="star">★</span>
        <span class="star">★</span>
      </div>
      <span class="review-count">(50 reviews)</span>
    </div>

    <div class="price-block">
      <span class="price" id="priceDisplay">₱600.00</span>
      <span class="price-original" id="origPrice">₱900.00</span>
      <span class="discount-badge">33% OFF</span>
    </div>

    <hr class="divider"/>

    <!-- VOLUMES -->
    <div>
      <div class="volume-label">Select Volume</div>
      <div class="volumes" id="volumeSelector">
        <button class="vol-btn active" data-vol="1" data-price="600" data-orig="900" data-sub="Infinite Castle Arc, Volume 1">Vol.1</button>
        <button class="vol-btn"        data-vol="2" data-price="550" data-orig="900" data-sub="Infinite Castle Arc, Volume 2">Vol.2</button>
        <button class="vol-btn"        data-vol="3" data-price="550" data-orig="900" data-sub="Infinite Castle Arc, Volume 3">Vol.3</button>
        <button class="vol-btn"        data-vol="4" data-price="500" data-orig="900" data-sub="Infinity Fortress Arc, Volume 1">Vol.4</button>
      </div>
    </div>

    <!-- QUANTITY -->
    <div>
      <div class="qty-label">Quantity</div>
      <div class="qty-control">
        <button class="qty-btn" id="qtyMinus">−</button>
        <input  class="qty-value" id="qtyValue" type="number" value="1" min="1" max="99" readonly/>
        <button class="qty-btn" id="qtyPlus">+</button>
      </div>
    </div>

    <!-- ACTIONS -->
    <div class="actions">
      <button class="btn btn-cart" id="cartBtn">Add to Cart</button>
      <button class="btn btn-buy"  id="buyBtn">Buy Now</button>
    </div>

  </div>
</div>

<!-- TOAST -->
<div id="toast"></div>

<script>
  // ── Volume switching ──
  const volBtns    = document.querySelectorAll('.vol-btn');
  const priceEl    = document.getElementById('priceDisplay');
  const origEl     = document.getElementById('origPrice');
  const subtitleEl = document.getElementById('subtitleText');

  volBtns.forEach(btn => {
    btn.addEventListener('click', () => {
      volBtns.forEach(b => b.classList.remove('active'));
      btn.classList.add('active');
      priceEl.textContent    = `₱${parseInt(btn.dataset.price).toLocaleString()}.00`;
      origEl.textContent     = `₱${parseInt(btn.dataset.orig).toLocaleString()}.00`;
      subtitleEl.textContent = btn.dataset.sub;
    });
  });

  // ── Quantity ──
  const qtyInput = document.getElementById('qtyValue');
  document.getElementById('qtyMinus').addEventListener('click', () => {
    if (parseInt(qtyInput.value) > 1) qtyInput.value = parseInt(qtyInput.value) - 1;
  });
  document.getElementById('qtyPlus').addEventListener('click', () => {
    if (parseInt(qtyInput.value) < 99) qtyInput.value = parseInt(qtyInput.value) + 1;
  });

  // ── Thumbnail click (wire up once you have real images) ──
  document.querySelectorAll('.thumb').forEach(t => {
    t.addEventListener('click', () => {
      document.querySelectorAll('.thumb').forEach(x => x.classList.remove('active'));
      t.classList.add('active');
    });
  });

  // ── Toast helper ──
  function showToast(msg) {
    const t = document.getElementById('toast');
    t.textContent = msg;
    t.classList.add('show');
    setTimeout(() => t.classList.remove('show'), 2800);
  }

  // ── Buttons ──
  document.getElementById('cartBtn').addEventListener('click', () => {
    const vol = document.querySelector('.vol-btn.active').textContent;
    const qty = qtyInput.value;
    showToast(`✓ Added ${qty}× Demon Slayer ${vol} to cart`);
  });

  document.getElementById('buyBtn').addEventListener('click', () => {
    showToast('Redirecting to checkout…');
  });
</script>
</body>
</html>
