-- Enable the pgcrypto extension for UUID generation (if you haven't already)
CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- Category table with UUID as the primary key
CREATE TABLE Categories (
    category_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    description TEXT,
    unit VARCHAR(50)
);

-- Users table with UUID as the primary key
CREATE TABLE Users (
    user_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username VARCHAR(255) UNIQUE NOT NULL,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);

-- Activities table with UUID foreign keys
CREATE TABLE Activities (
    activity_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    category_id UUID NOT NULL,
    user_id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    FOREIGN KEY (category_id) REFERENCES Categories (category_id),
    FOREIGN KEY (user_id) REFERENCES Users (user_id)
);

-- ActivityLogs table with UUID foreign keys
CREATE TABLE ActivityLogs (
    log_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    activity_id UUID NOT NULL,
    user_id UUID NOT NULL,
    timestamp TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    quantity DECIMAL(10, 2),
    lat NUMERIC(10, 6),
    lng NUMERIC(10, 6),
    FOREIGN KEY (activity_id) REFERENCES Activities (activity_id),
    FOREIGN KEY (user_id) REFERENCES Users (user_id)
);

-- ActivityTemplates table with UUID foreign keys
CREATE TABLE ActivityTemplates (
    template_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    category_id UUID NOT NULL,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    default_quantity DECIMAL(10, 2),
    FOREIGN KEY (category_id) REFERENCES Categories (category_id)
);
    
