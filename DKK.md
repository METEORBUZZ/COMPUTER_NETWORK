System.Security.Cryptography.X509Certificates.X509Store("TrustedPublisher", "LocalMachine")
$storePub.Open("ReadWrite")
$storePub.Add($cert)
$storePub.Close()

# Apply Digital Signature to kind.exe
Set-AuthenticodeSignature -FilePath "C:\tools\kind.exe" -Certificate $cert
```

---

## 🔍 Verification Commands

Run these commands in terminal to check if everything is configured properly:

```powershell
docker --version
kind --version
kubectl version --client
```

### Expected Output
```text
install sucessfully
  - Docker:  Docker version 27.x.x, build ...
  - Kind:    kind version 0.33.0
  - Kubectl: gitVersion: v1.31.x
```
