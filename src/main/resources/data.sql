INSERT INTO users (
    account_status,
    created_at,
    email,
    favorite_color,
    full_name,
    password,
    phone,
    transaction_pin,
    role_id
) VALUES (
    'ACTIVE',
    CURRENT_TIMESTAMP,
    'admin@revpay.com',
    'Black',
    'System Admin',
    '$2a$10$7QJkGZkFq8hQmXWmZk6vA.xF6JqP1nTz3CwR9Lx8mYb0uVdE1sH2K',
    '9999999999',
    '$2a$10$7QJkGZkFq8hQmXWmZk6vA.xF6JqP1nTz3CwR9Lx8mYb0uVdE1sH2K',
    3
);