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


🚀 CI/CD Flow (Application Pipeline)

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

🐳 Docker & Image Strategy

Each service builds its own Docker image
Images are pushed to:
Amazon ECR (private)
Image tags are:
Git commit based / pipeline generated
Helm charts use:
Image tag injected by GitOps




### Platform

- AWS VPC with public and private subnets
- Amazon EKS cluster
- Managed node groups
- NGINX Ingress Controller
- cert-manager (optional TLS)
- Cluster add-ons via Terraform

### Security

- IAM roles for service accounts (IRSA)
- GitHub Actions authenticated via AWS OIDC
- No static AWS keys in CI/CD
- Least-privilege IAM policies

---

## 🔁 GitOps Deployment Model

All Kubernetes deployments are managed using GitOps.

Flow:

Developer Push → GitHub Actions → GitOps Repo → ArgoCD → EKS Cluster

ArgoCD continuously monitors the GitOps repository and syncs changes automatically.

No manual kubectl deployments are used.

---

## 🔄 CI/CD Pipeline Flow

Each microservice has its own GitHub Actions workflow.

Pipeline stages:

1. Trigger on push to `prod` branch
2. Build Docker image
3. Authenticate to AWS using OIDC
4. Push image to Amazon ECR
5. Update image tag in GitOps repository
6. Commit GitOps change
7. ArgoCD deploys automatically

This provides continuous delivery with full traceability.

---

## 📦 Container Registry

Private Amazon ECR repositories:

- demo-private/ui
- demo-private/catalog
- demo-private/cart
- demo-private/orders
- demo-private/checkout

Each service pushes to its own repository.

---

## 📁 Repository Structure

This platform uses three functional repositories.

### 🧱 Infrastructure (Terraform)

Repo: **retail-store-infra**

Responsible for:

- VPC
- EKS cluster
- IAM roles
- OIDC provider
- ArgoCD installation
- Cluster add-ons

---

### 🚀 Application & CI

Repo: **retail-store-app**

Contains:

- Microservice source code
- Dockerfiles
- Helm charts
- GitHub Actions pipelines

Responsible for building and publishing images.

---

### 🔁 GitOps Deployment State

Repo: **retail-store-gitops**

Contains:

- Helm values
- Environment configs
- ArgoCD application definitions

Represents the desired state of Kubernetes.

---

## 🎯 Skills Demonstrated

This project demonstrates:

- AWS cloud architecture
- Kubernetes production deployment
- Terraform Infrastructure as Code
- Secure CI/CD using OIDC
- GitOps operational model
- Microservices system design
- Observability-ready workloads

---

## 👤 Author

Ali Haider  
IT Infrastructure & Cloud Engineer  
Linux | AWS | Kubernetes | Terraform | DevOps Automation  

GitHub: https://github.com/iam-alehaider
# test ci
