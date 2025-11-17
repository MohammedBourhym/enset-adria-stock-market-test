-- Init script for PostgreSQL
-- Creates schemas if needed

-- Database is already created by POSTGRES_DB env variable
-- Just ensure we're using the right database
\c stockmarket;

-- Create schemas or tables if needed
-- Tables will be auto-created by Hibernate with ddl-auto=update
