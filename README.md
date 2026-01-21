## 🛒 CloudMart Microservices Applications & CI

This repository contains the source code, Docker configurations, Helm charts, and CI triggers for the CloudMart retail microservices application deployed on AWS EKS using GitOps (ArgoCD).

It works together with:

1) Infrastructure Repo: iam-alehaider/cloudmart-infra (Terraform → EKS, VPC, Addons).
2) GitOps Repo: iam-alehaider/cloudmart-gitops (ArgoCD applications & Helm releases). 


---

## 🧩 Microservices Architecture

The application is intentionally designed as multiple de-coupled services.

| Service  | Language | Description |
|--------|----------|------------|
| UI | Java | Web frontend |
| Catalog | Go | Product catalog API |
| Cart | Java | Shopping cart API |
| Orders | Java | Order management API |
| Checkout | Node.js | Checkout orchestration API |

Each service:
- Has its own Docker image
- Has its own Helm chart
- Is deployed independently

---

## 🗂 Repository Structure


```text

cloudmart-microservices-apps/
│
├── src/
│   ├── ui/
│   │   ├── chart/          # Helm chart
│   │   ├── Dockerfile
│   │   └── application code
│   │
│   ├── catalog/
│   ├── cart/
│   ├── orders/
│   └── checkout/
│
└── .github/workflows/      # CI pipelines (GitHub Actions)

---


## 🚀 CI/CD Flow (Application Pipeline)

This repo handles CI only.
CD is handled by ArgoCD via GitOps repo.

🔁 Step-by-Step Flow
Developer pushes code to prod branch
GitHub Actions pipeline runs:
Build application
Build Docker image
Push image to AWS ECR
Pipeline updates image tag in GitOps repo
ArgoCD detects change
ArgoCD deploys new version to EKS
🎯 Result
Fully automated deployment using GitOps best practices.



## 🐳 Docker & Image Strategy

Each service builds its own Docker image
Images are pushed to:
Amazon ECR (private)
Image tags are:
Git commit based / pipeline generated
Helm charts use:
Image tag injected by GitOps


## 📦 Container Registry

Private Amazon ECR repositories:

- demo-private/ui
- demo-private/catalog
- demo-private/cart
- demo-private/orders
- demo-private/checkout

Each service pushes to its own repository.

---

##☸ Kubernetes Deployment (Helm)

Each service includes a full Helm chart with:
✅ Common Features
Deployment with RollingUpdate
Readiness & Liveness probes
Non-root containers
Read-only root filesystem
ConfigMaps for configuration
Optional HPA (Horizontal Pod Autoscaler)
PodDisruptionBudget
Topology spread constraints

---
## 🧠 Service-Specific Features

## 🛒 UI Service

Ingress with NGINX
cert-manager TLS (Let’s Encrypt)
Multi-ingress support
Optional AI Chat integration (OpenAI / Bedrock)
Prometheus metrics endpoint

## 📦 Orders Service

Optional PostgreSQL (internal or external)
Optional RabbitMQ messaging
Secret auto-generation
Persistent volume support

## 💳 Checkout Service

Optional Redis backend
In-memory mode supported
Prometheus metrics scraping

## 🔐 Security Practices

Dedicated ServiceAccounts
Pod Security Context:
runAsNonRoot
fsGroup: 1000
Optional AWS Security Group for Pods
Secrets managed via Kubernetes Secrets
No hardcoded credentials

---

## 📊 Observability

All services support:
Prometheus metrics scraping
Grafana dashboards (from monitoring stack)
Centralized logging via:
Promtail → Loki
Monitoring stack is deployed from cloudmart-infra repo.

---

## 👤 Author

Ali Haider DevOps / Cloud Engineer /linux
