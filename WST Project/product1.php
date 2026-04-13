<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>PRODUCTS</title>
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

   
    .top-header {
      background: #0d0d0d;
      border-bottom: 3px solid var(--red);
      display: flex;
      align-items: center;
      justify-content: space-between;
      padding: 1.3rem 2.5rem;
      gap: 1rem;
    }

    .logo {
      font-family: 'Barlow Condensed', sans-serif;
      font-size: 2rem;
      font-weight: 800;
      letter-spacing: 0.03em;
      color: #fff;
    }

    .logo span {
      color: var(--red);
    }

    .search-bar {
      flex: 1;
      max-width: 520px;
      position: relative;
    }

    .search-bar input {
      width: 100%;
      background: #1c1c1c;
      border: 1px solid var(--border);
      color: var(--text);
      padding: 0.85rem 3rem 0.85rem 1rem;
      border-radius: 8px;
      font-size: 0.95rem;
    }

    .search-bar input:focus {
      outline: none;
      border-color: var(--red);
    }

    .search-bar .icon {
      position: absolute;
      right: 1rem;
      top: 50%;
      transform: translateY(-50%);
      color: var(--muted);
      font-size: 1.1rem;
      pointer-events: none;
    }

    .profile-circle {
      width: 46px;
      height: 46px;
      border-radius: 50%;
      background: #2a2a2a;
      border: 1px solid var(--border);
      flex-shrink: 0;
    }


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
      font-size: 0.9rem;
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


    .products-section {
      max-width: 1250px;
      margin: 3rem auto 4rem;
      padding: 0 2.5rem;
    }

    .section-header {
      display: flex;
      align-items: center;
      justify-content: space-between;
      gap: 1rem;
      margin-bottom: 1.8rem;
      flex-wrap: wrap;
    }

    .section-title {
      display: flex;
      align-items: center;
      gap: 0.85rem;
    }

    .section-title .line {
      width: 4px;
      height: 34px;
      background: var(--red);
      border-radius: 2px;
    }

    .section-title h2 {
      font-family: 'Barlow Condensed', sans-serif;
      font-size: 2.2rem;
      letter-spacing: 0.05em;
      color: #fff;
      text-transform: uppercase;
    }

    .view-all {
      background: var(--surface2);
      border: 1px solid var(--border);
      color: var(--muted);
      padding: 0.7rem 1.2rem;
      border-radius: 6px;
      font-family: 'Barlow Condensed', sans-serif;
      font-size: 0.9rem;
      font-weight: 700;
      letter-spacing: 0.08em;
      text-transform: uppercase;
      cursor: pointer;
      transition: 0.2s;
    }

    .view-all:hover {
      color: var(--text);
      border-color: var(--text);
    }


    .filters {
      display: flex;
      gap: 0.7rem;
      flex-wrap: wrap;
      margin-bottom: 2.2rem;
    }

    .filter-btn {
      padding: 0.6rem 1.15rem;
      border-radius: 999px;
      border: 1px solid var(--border);
      background: #181818;
      color: var(--muted);
      font-family: 'Barlow Condensed', sans-serif;
      font-size: 0.85rem;
      font-weight: 700;
      letter-spacing: 0.08em;
      text-transform: uppercase;
      cursor: pointer;
      transition: all 0.2s;
    }

    .filter-btn:hover {
      border-color: var(--red);
      color: var(--text);
    }

    .filter-btn.active {
      background: var(--red);
      color: #fff;
      border-color: var(--red);
    }


    .product-grid {
      display: grid;
      grid-template-columns: repeat(5, 1fr);
      gap: 1.2rem;
    }

    .product-card {
      background: #161616;
      border: 1px solid var(--border);
      border-radius: 8px;
      padding: 1rem;
      transition: transform 0.2s, border-color 0.2s, box-shadow 0.2s;
      display: flex;
      flex-direction: column;
    }

    .product-card:hover {
      transform: translateY(-4px);
      border-color: var(--red);
      box-shadow: 0 10px 28px rgba(0,0,0,0.35);
    }

    .product-image {
      width: 100%;
      aspect-ratio: 3/4;
      border-radius: 5px;
      overflow: hidden;
      background: var(--surface2);
      margin-bottom: 0.9rem;
      border: 1px solid #252525;
    }

    .product-image img {
      width: 100%;
      height: 100%;
      object-fit: cover;
      display: block;
      transition: transform 0.3s ease;
    }

    .product-card:hover .product-image img {
      transform: scale(1.03);
    }

    .product-title {
      font-size: 0.95rem;
      font-weight: 600;
      color: #f4f4f4;
      line-height: 1.35;
      min-height: 48px;
      margin-bottom: 0.9rem;
    }

    .product-price {
      font-family: 'Barlow Condensed', sans-serif;
      font-size: 2rem;
      font-weight: 800;
      color: #fff;
      margin-bottom: 0.35rem;
    }

    .rating {
      display: flex;
      align-items: center;
      gap: 0.45rem;
      margin-bottom: 1rem;
    }

    .stars {
      color: var(--gold);
      font-size: 0.95rem;
      letter-spacing: 0.03em;
    }

    .review-count {
      font-size: 0.78rem;
      color: var(--muted);
    }

    .btn-cart {
      margin-top: auto;
      width: 100%;
      padding: 0.85rem 1rem;
      font-family: 'Barlow Condensed', sans-serif;
      font-size: 0.85rem;
      font-weight: 700;
      letter-spacing: 0.12em;
      text-transform: uppercase;
      border-radius: 5px;
      border: none;
      background: var(--red);
      color: #fff;
      cursor: pointer;
      transition: all 0.2s;
    }

    .btn-cart:hover {
      background: var(--red-dark);
      transform: translateY(-1px);
      box-shadow: 0 6px 18px rgba(224,32,32,0.35);
    }


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


    @media (max-width: 1150px) {
      .product-grid {
        grid-template-columns: repeat(3, 1fr);
      }
    }

    @media (max-width: 820px) {
      .top-header {
        flex-wrap: wrap;
        justify-content: center;
      }

      .search-bar {
        order: 3;
        width: 100%;
        max-width: 100%;
      }

      .product-grid {
        grid-template-columns: repeat(2, 1fr);
      }
    }

    @media (max-width: 560px) {
      .products-section {
        padding: 0 1.2rem;
      }

      nav {
        flex-wrap: wrap;
      }

      nav a {
        padding: 1rem 1.2rem;
      }

      .product-grid {
        grid-template-columns: 1fr;
      }

      .section-title h2 {
        font-size: 1.8rem;
      }
    }
  </style>
</head>
<body>


<header class="top-header">
  <div class="logo">MANGA<span>QUILLA</span></div>

  <div class="search-bar">
    <input type="text" placeholder="Search manga..." />
    <span class="icon">🔍</span>
  </div>

  <div class="profile-circle"></div>
</header>


<nav>
  <a href="#">Home</a>
  <a href="#" class="active">Products</a>
  <a href="#">New Arrivals</a>
  <a href="#">About Us</a>
</nav>


<section class="products-section">

  <div class="section-header">
    <div class="section-title">
      <div class="line"></div>
      <h2>Products</h2>
    </div>

    <button class="view-all">View All</button>
  </div>


  <div class="filters" id="filterButtons">
    <button class="filter-btn active" data-filter="all">All</button>
    <button class="filter-btn" data-filter="action">Action</button>
    <button class="filter-btn" data-filter="fantasy">Fantasy</button>
    <button class="filter-btn" data-filter="scifi">Sci-Fi</button>
    <button class="filter-btn" data-filter="isekai">Isekai</button>
    <button class="filter-btn" data-filter="romance">Romance</button>
  </div>


  <div class="product-grid" id="productGrid">

    <div class="product-card" data-category="action fantasy">
      <div class="product-image">
        <img src="https://upload.wikimedia.org/wikipedia/en/4/46/Jujutsu_kaisen.jpg" alt="Jujutsu Kaisen">
      </div>
      <div class="product-title">Jujutsu Kaisen</div>
      <div class="product-price">₱600.00</div>
      <div class="rating">
        <div class="stars">★★★★★</div>
        <div class="review-count">(50)</div>
      </div>
      <button class="btn-cart">Add to Cart</button>
    </div>

    <div class="product-card" data-category="action fantasy">
      <div class="product-image">
        <img src="https://m.media-amazon.com/images/I/51jl6fJ86AL._SY445_SX342_.jpg" alt="Naruto">
      </div>
      <div class="product-title">Naruto: Shippuden</div>
      <div class="product-price">₱600.00</div>
      <div class="rating">
        <div class="stars">★★★★★</div>
        <div class="review-count">(50)</div>
      </div>
      <button class="btn-cart">Add to Cart</button>
    </div>

    <div class="product-card" data-category="action fantasy">
      <div class="product-image">
        <img src="https://comicbook.com/wp-content/uploads/sites/4/2025/03/One-Piece-Volume-108.jpg?w=683" alt="One Piece">
      </div>
      <div class="product-title">One Piece</div>
      <div class="product-price">₱600.00</div>
      <div class="rating">
        <div class="stars">★★★★★</div>
        <div class="review-count">(50)</div>
      </div>
      <button class="btn-cart">Add to Cart</button>
    </div>

    <div class="product-card" data-category="action fantasy">
      <div class="product-image">
        <img src="https://m.media-amazon.com/images/I/81vbN16NtXL.jpg" alt="Bleach">
      </div>
      <div class="product-title">Bleach</div>
      <div class="product-price">₱600.00</div>
      <div class="rating">
        <div class="stars">★★★★★</div>
        <div class="review-count">(50)</div>
      </div>
      <button class="btn-cart">Add to Cart</button>
    </div>

    <div class="product-card" data-category="action fantasy">
      <div class="product-image">
        <img src="https://dosbg3xlm0x1t.cloudfront.net/images/items/9784088846767/1200/9784088846767.jpg" alt="One Punch Man">
      </div>
      <div class="product-title">One Punch Man</div>
      <div class="product-price">₱600.00</div>
      <div class="rating">
        <div class="stars">★★★★★</div>
        <div class="review-count">(50)</div>
      </div>
      <button class="btn-cart">Add to Cart</button>
    </div>

  </div>
</section>


<div id="toast"></div>

<script>

  const filterBtns = document.querySelectorAll('.filter-btn');
  const cards = document.querySelectorAll('.product-card');

  filterBtns.forEach(btn => {
    btn.addEventListener('click', () => {
      filterBtns.forEach(b => b.classList.remove('active'));
      btn.classList.add('active');

      const filter = btn.dataset.filter;

      cards.forEach(card => {
        const category = card.dataset.category;

        if (filter === 'all' || category.includes(filter)) {
          card.style.display = 'flex';
        } else {
          card.style.display = 'none';
        }
      });
    });
  });


  function showToast(msg) {
    const t = document.getElementById('toast');
    t.textContent = msg;
    t.classList.add('show');
    setTimeout(() => t.classList.remove('show'), 2800);
  }


  document.querySelectorAll('.btn-cart').forEach(btn => {
    btn.addEventListener('click', () => {
      const title = btn.closest('.product-card').querySelector('.product-title').textContent;
      showToast(`✓ Added ${title} to cart`);
    });
  });
</script>
</body>
</html>