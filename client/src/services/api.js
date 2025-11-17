import axios from 'axios';

const API_BASE_URL = process.env.REACT_APP_API_URL || 'http://localhost:8888';

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Company Service
export const companyService = {
  getAll: () => api.get('/company/api/companies'),
  getById: (id) => api.get(`/company/api/companies/${id}`),
  getByDomain: (domain) => api.get(`/company/api/companies/domain/${domain}`),
  create: (data) => api.post('/company/api/companies', data),
  updatePrice: (id, price) => api.patch(`/company/api/companies/${id}/price`, { currentStockPrice: price }),
  delete: (id) => api.delete(`/company/api/companies/${id}`),
};

// Stock Service
export const stockService = {
  getAll: () => api.get('/stock/api/stocks'),
  getById: (id) => api.get(`/stock/api/stocks/${id}`),
  getByCompanyId: (companyId) => api.get(`/stock/api/stocks/company/${companyId}`),
  create: (data) => api.post('/stock/api/stocks', data),
  delete: (id) => api.delete(`/stock/api/stocks/${id}`),
  updateCompanyPrice: (companyId) => api.post(`/stock/api/stocks/update-price/${companyId}`),
};

export default api;
