<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Manga Profile</title>

<style>
body {
    background: #2b2b2b;
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 20px;
}

.container {
    width: 100%;
    max-width: 1200px;
    margin: 60px auto;
    background: linear-gradient(to right, #0a0a0a, #2a0000);
    min-height: 70vh;
    padding: 50px;
    border-radius: 12px;
    border: 1px solid #555;
    box-shadow: 0 0 20px rgba(0,0,0,0.6);
    position: relative;
}

.title {
    color: red;
    font-weight: bold;
    margin-bottom: 20px;
    display: flex;
    align-items: center;
    gap: 20px;
}

.back-btn {
    display: inline-block;
    color: white;
    text-decoration: none;
    font-size: 14px;
    background: #111;
    padding: 8px 15px;
    border-radius: 6px;
    border: 1px solid #555;
    transition: 0.3s;
}

.back-btn:hover {
    background: red;
    color: white;
    border-color: red;
}

.grid {
    display: grid;
    grid-template-columns: 1fr 2fr;
    gap: 20px;
}

.card {
    background: #111;
    border-radius: 10px;
    padding: 20px;
    color: white;
}

.right-top {
    height: 120px;
}

.right-bottom {
    border: 2px solid #00aaff;
}

.avatar {
    width: 100px;
    height: 100px;
    border-radius: 50%;
    background: gray;
    margin-bottom: 10px;
    display: block;
    margin-left: auto;
    margin-right: auto;
}

.btn {
    background: red;
    color: white;
    padding: 8px 15px;
    border: none;
    margin-top: 10px;
    cursor: pointer;
    border-radius: 6px;
    width: 100%;
    font-size: 14px;
}

.btn:hover {
    background: #cc0000;
}

.stats {
    display: flex;
    justify-content: space-around;
    margin-top: 10px;
    text-align: center;
}

.stats div {
    flex: 1;
}

.stats h2 {
    margin: 0;
    color: #00aaff;
    font-size: 2em;
}

ul {
    margin: 10px 0;
    padding-left: 20px;
}

li {
    margin: 5px 0;
    font-size: 14px;
}

@media (max-width: 768px) {
    .grid {
        grid-template-columns: 1fr;
    }
    
    .title {
        flex-direction: column;
        align-items: flex-start;
    }
}
</style>

</head>
<body>

<?php
// DATABASE CONNECTION (SAMPLE)
function getUserData($user_id) {
    // Sample data - replace with real database query
    return [
        'username' => 'Aquilla',
        'member_since' => 'Jan 2026',
        'fav_genre' => 'Action / Romance',
        'avatar' => 'avatar.jpg',
        'total_read' => 120,
        'favorites' => 25,
        'chapters_read' => 560
    ];
}

// Get reading history
function getReadingHistory($user_id, $limit = 3) {
    // Sample data - replace with real database query
    return [
        "Demon Slayer - Chapter 120",
        "Attack on Titan - Chapter 80",
        "Jujutsu Kaisen - Chapter 200"
    ];
}

// MAIN LOGIC
$user_id = isset($_GET['id']) ? (int)$_GET['id'] : 1;
$user_data = getUserData($user_id);
$history = getReadingHistory($user_id);

// Handle form actions
if ($_POST['action'] ?? '' === 'edit_profile') {
    // Process edit profile
    header('Location: profile.php?id=' . $user_id . '&saved=1');
    exit;
}

$message = '';
if (isset($_GET['saved'])) {
    $message = '<div style="color: #00ff00; padding: 10px; background: #004400; border-radius: 5px; margin-bottom: 20px;">Profile updated successfully!</div>';
}
?>

<div class="container">
    <?php echo $message; ?>
    
    <h2 class="title">
        <a href="dashboard.php" class="back-btn">← Back</a>
        PROFILE
    </h2>

    <div class="grid">
        
        <!-- LEFT CARD - USER INFO -->
        <div class="card">
            <img src="images/<?php echo htmlspecialchars($user_data['avatar']); ?>" 
                 class="avatar" alt="Avatar" onerror="this.style.background='gray'">
            <h3><?php echo htmlspecialchars($user_data['username']); ?></h3>
            <p>Member Since: <?php echo htmlspecialchars($user_data['member_since']); ?></p>
            <p>Favorite Genre: <?php echo htmlspecialchars($user_data['fav_genre']); ?></p>

            <form method="POST" style="display: inline;">
                <input type="hidden" name="action" value="edit_profile">
                <button type="submit" class="btn">EDIT PROFILE</button>
            </form>
        </div>

        <!-- RIGHT SIDE -->
        <div>
            
            <!-- TOP RIGHT (READING HISTORY) -->
            <div class="card right-top">
                <h3>Reading History (<?php echo count($history); ?>)</h3>
                <?php if (empty($history)): ?>
                    <p style="color: #888;">No reading history yet.</p>
                <?php else: ?>
                    <ul>
                        <?php foreach(array_slice($history, 0, 5) as $manga): ?>
                            <li><?php echo htmlspecialchars($manga); ?></li>
                        <?php endforeach; ?>
                    </ul>
                <?php endif; ?>
            </div>

            <!-- BOTTOM RIGHT (STATS) -->
            <div class="card right-bottom">
                <h3>Manga Stats</h3>

                <div class="stats">
                    <div>
                        <h2><?php echo number_format($user_data['total_read']); ?></h2>
                        <p>Manga Read</p>
                    </div>

                    <div>
                        <h2><?php echo number_format($user_data['favorites']); ?></h2>
                        <p>Favorites</p>
                    </div>

                    <div>
                        <h2><?php echo number_format($user_data['chapters_read']); ?></h2>
                        <p>Chapters</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>